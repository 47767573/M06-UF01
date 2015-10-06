package Practica2;

import java.io.*;

public class Enjaular {

	
	
	public static void guardar (Animal a, String path) throws IOException, ClassNotFoundException{	
		try{
			
			//Crear el objectInputStream
			String ruta = path+"/"+a.getNombre()+".moi";

			FileInputStream fis = new FileInputStream (ruta);
			ObjectInputStream ois = new ObjectInputStream(fis);	
			
			//Escribir (guardar) el animal
			String descripcion = "nombre:"+a.getNombre()+","+a.getNumero()+","+a.getCodi();
			ois.;
			
			String mois = (String)ois.readObject();
		
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}
		
	}
	
	public void guardar (Animal a, File fichero){	
		
	}
	
	public Animal soltar (String path){
		Animal an = new Animal();
		return an;
	}
	
	public Animal soltar (File fichero){
		Animal an = new Animal();
		return an;
	}

}
