import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Moises on 31/10/2015.
 */
public class forecast {

    static String lugar ="";
    static ArrayList<String> tiempoTemperatura = new ArrayList<>();
    static ArrayList<String> tiempoEstado = new ArrayList<>();
    static ArrayList<String> tiempoFechaHora = new ArrayList<>();
    static ArrayList<String> tiempoViento = new ArrayList<>();

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        Scanner scan = new Scanner(System.in);

        String ruta = "C:\\Users\\Moises\\Desktop\\Git\\M06-UF01\\src\\Practica4\\src\\forecast.xml";

        //System.out.println("Introduzca ruta de fichero");
        //ruta = scan.next();
        //boolean fileExist = true;

        //INICIO DEL PUNTO A :::::::::::::::::::::::EXTRAER RESUMEN DEL XML::::::::::::::::

        System.out.println("Inicio del punto A");
        File fichero = new File(ruta);

        if (!isXml (fichero)){
            System.out.println("El archivo no es del tipo XML");
        }else if (fichero.exists()) {
            System.out.println("Creando resumen");

            //inicio de escritura por pantalla y guardado del archivo
            try {
                DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
                DocumentBuilder DomB = dBF.newDocumentBuilder();
                Document xmlTxt = DomB.parse(fichero);

                //Obtenemos el elemento raiz del documento
                Element raiz = xmlTxt.getDocumentElement();
                NodeList hijosRaiz = raiz.getChildNodes();

                //Guardar el nombre de la localización
                Node lugarNodo = hijosRaiz.item(0);
                lugar = lugarNodo.getChildNodes().item(0).getTextContent();

                //Guardar la temperatura, el tiempo y velocidad del tiempo por cada intervalo de hora
                Node tiempoNodo = hijosRaiz.item(4);
                NodeList hijosTiempo = tiempoNodo.getChildNodes();

                System.out.println("\t"+lugar.toUpperCase());
                System.out.println("::::::::::::::::::::::::::::::::::::::\n");

                for (int i=0 ; i<hijosTiempo.getLength() ; i++ ) {
                    Node tiempo = hijosTiempo.item(i);

                    String fecha = tiempo.getAttributes().item(1).getNodeValue();

                    String estado = tiempo.getChildNodes().item(0).getAttributes().item(0).getNodeValue();

                    String vientoMps = tiempo.getChildNodes().item(3).getAttributes().item(0).getNodeValue();
                    String vientoKmp = mpsToKpmConvertor(vientoMps);        //convertirlo el valor de velocidad de viento de mps a Kpm

                    String temperatura = tiempo.getChildNodes().item(4).getAttributes().item(3).getNodeValue();

                    //Guardar de los datos del tiempo
                    tiempoFechaHora.add(i,fecha);
                    tiempoEstado.add(i,estado);
                    tiempoViento.add(i,vientoKmp);
                    tiempoTemperatura.add(i,temperatura);

                    //Imprimir resultado de cada intervalo de hora por pantalla
                    System.out.println("Fecha/Hora: "+fecha
                            +"\nEstado: "+toSpanish(estado)
                            +"\nViento(k/h): "+vientoKmp
                            +"\nTemperatura: "+temperatura
                            +"\n______________________________________\n");
                }
            }catch (DOMException e){
                System.out.println("Error de lectura de archivo XML en el Punto A"+e);
            }catch (IOException e){
                System.out.println("Error de lectura o guardado de fichero en el Punto A"+e);
            }catch (ParserConfigurationException e){
                System.out.println("Error parseando XML en el Punto A"+e);
            }
        }else System.out.println("Compruebe que el archivo exista o no sea un directorio");



        //INICIO DEL PUNTO B :::::::::::::::::::CREAR XML CON RESUMEN::::::::::::::::::::::

        try{
            DocumentBuilderFactory dbfNuevo = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbNuevo = dbfNuevo.newDocumentBuilder();
            Document dom = dbNuevo.newDocument();

            //Creamos el elemento raiz y lo añadimos
            Element raizElemento = dom.createElement("ElTiempo");
            dom.appendChild(raizElemento);

            //Creamos el elemento lugar y lo añadimo
            Element ciudadElemento = dom.createElement("Ciudad");
            Text ciudadText = dom.createTextNode(lugar);
            ciudadElemento.appendChild(ciudadText);
            raizElemento.appendChild(ciudadElemento);

            Element meteoElemento = dom.createElement("Meteorologia");
            raizElemento.appendChild(meteoElemento);

            for (int i=0 ; i<tiempoFechaHora.size() ; i++){

                //Creamos el elemento fecha, su atributo y lo introducimos en su elemento padre Meteorologia
                Element fechaElemento = dom.createElement("Tiempo");
                fechaElemento.setAttribute("fechaHora",tiempoFechaHora.get(i));
                meteoElemento.appendChild(fechaElemento);

                //Creamos el elemento estado, su atributo y lo introducimos en su elemento padre Tiempo
                Element estadoElemento = dom.createElement ("Estado");
                estadoElemento.setAttribute("nombre",toSpanish(tiempoEstado.get(i)));
                fechaElemento.appendChild(estadoElemento);

                //Creamos el elemento viento, su atributo y lo introducimos en su elemento padre Tiempo
                Element vientoElemento = dom.createElement ("Viento");
                vientoElemento.setAttribute("kph",tiempoViento.get(i));
                fechaElemento.appendChild(vientoElemento);

                //Creamos el elemento temperatura, su atributo y lo introducimos en su elemento padre Tiempo
                Element temperaturaElemento = dom.createElement ("Temperatura");
                temperaturaElemento.setAttribute("Celsius",tiempoTemperatura.get(i));
                fechaElemento.appendChild(temperaturaElemento);
            }


            //Creamos el fichero del xml nuevo especificando el formato
            File ficheroNuevo = new File("C:\\Users\\Moises\\Desktop\\Git\\M06-UF01\\src\\Practica4\\src\\forecastResumido.xml");
            OutputFormat oFormat = new OutputFormat(dom);
            oFormat.setIndenting(true);

            FileOutputStream fos = new FileOutputStream(ficheroNuevo);
            XMLSerializer serializer = new XMLSerializer(fos,oFormat);

            serializer.serialize(dom);

        }catch(DOMException e){
            System.out.println("Error de creación del cuerpo del XML" + e);
        }catch (IOException e) {
            System.out.println("Error de lectura o guardado de fichero en el Punto A" + e);
        }

    }
    public static boolean isXml (File file){
        boolean valido = false;
        String extension = ".xml";

        System.out.print("validando si es XML...");
        int locDot = file.getPath().indexOf(".");
        if (file.getPath().substring(locDot, file.getPath().length()).equals(extension)) {
            valido=true;
            System.out.println("...es XML valido");
        }
        return valido;
    }

    public static String mpsToKpmConvertor (String mps) {
        double mpsD = Double.parseDouble(mps);
        double kpmD = Math.round(mpsD * 3.62);

        return String.valueOf(kpmD);
    }

    public static String toSpanish (String s) {
        if (s.equals("scattered clouds")) return "Poco nublado";
        else if (s.equals("broken clouds")) return "Medianamente nublado";
        else if (s.equals("light rain")) return "lluvia ligera";
        else if (s.equals("overcast clouds")) return "Muy Nublado";
        else if (s.equals("few clouds")) return "Nublado";
        else if (s.equals("sky is clear")) return "Cielo claro";
        else return "Indeterminado";
    }

}
