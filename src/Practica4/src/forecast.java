import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Moises on 31/10/2015.
 */
public class forecast {


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        Scanner scan = new Scanner(System.in);

        String ruta = "C:\\Users\\Moises\\Desktop\\Git\\M06-UF01\\src\\Practica4\\src\\forecast.xml";

        //System.out.println("Introduzca ruta de fichero");
        //ruta = scan.next();
        boolean fileExist = false;

        //INICIO DEL PUNTO A ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

        File fichero = new File(ruta);

        if (!isXml (fichero)){
            System.out.println("El archivo no es del tipo XML");
        }else if (fileExist) {
            System.out.println("El archivo es del tipo XML");



            //inicio de escritura por pantalla del archivo
            try{
                DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
                DocumentBuilder DomB = dBF.newDocumentBuilder();
                Document xmlTxt = DomB.parse(fichero);

                //Obtenemos el elemento raiz del documento
                Element raiz = xmlTxt.getDocumentElement();


                //Obtener la lista de nodos de location y recorrer todas las listas con este tag
                NodeList lugar = raiz.getElementsByTagName("location");



                System.out.println(lugar.toString());



            }catch (DOMException e){
                System.out.println("Error de lectura"+e);
            }


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
}
