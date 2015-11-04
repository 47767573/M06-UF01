package Practica2;

import java.io.*;


public class Enjaular {	
	
	static Animal pet = new Animal();
	static String ruta;
	
	public static void guardar (Animal a, String path) throws IOException, FileNotFoundException{
		
		try{					
			//Preparamos el archivo para inscribir
			ruta = path+"/"+a.getNombre()+".txt";
			pet.setNombre(a.getNombre());
			pet.setNumero(a.getNumero());
			pet.setCodi(a.getCodi());
			
			
			//Almacenamos el animal serializado
			FileOutputStream fos = new FileOutputStream(ruta);			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(pet);
			oos.close();
							
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		}		
	}
	
	public void guardar (Animal a, File fichero) throws IOException, FileNotFoundException{	
		
		try{
			//Almacenamos el animal serializado
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(a);
			oos.close();	
			
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		}		
	}
	
	public static void soltar () throws IOException, FileNotFoundException, ClassNotFoundException{
		
		try{
			//Leer el animal serializado
			FileInputStream fis = new FileInputStream(ruta);
			ObjectInputStream ois = new ObjectInputStream(fis);			
			Animal a = (Animal)ois.readObject();
			System.out.println(a.getNombre());
			ois.close();
			
		}catch (ClassNotFoundException e1){
			e1.printStackTrace();	
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		}
	}

}
