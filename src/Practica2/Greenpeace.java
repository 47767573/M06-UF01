package Practica2;

import java.io.*;


public class Greenpeace {

	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		try{			
			Enjaular.soltar();		
			
		}catch (FileNotFoundException e1){
			e1.printStackTrace();
		}catch (IOException e2){
			e2.printStackTrace();
		}		
	}
}
