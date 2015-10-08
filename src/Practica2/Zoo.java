package Practica2;

import java.io.IOException;
import java.util.Scanner;




public class Zoo {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		Scanner sc = new Scanner (System.in);
				
		String nombreAnimal;
		String numeroAnimal;
		String codigoAnimal="";
		
		String parentPath = "/home/47767573t";
		
		boolean salir = false;
		boolean valido = false;
		
		while (!salir){
			System.out.println("\nNombre: ");
			nombreAnimal = sc.next();
			
			System.out.println("\nNumero: ");
			numeroAnimal = sc.next();
			
			while (!valido){
				System.out.println("\nCodigo: ");
				codigoAnimal = sc.next();
				
				valido = (Animal.esCodigoValido(codigoAnimal));
				
				if (valido == false) System.out.println("codigo incorrecto! hint:(EX,EW,CR,EN,VU,NT o LC)");				
			}
			
			Animal a = new Animal (nombreAnimal, numeroAnimal, codigoAnimal);
			Enjaular.guardar(a,parentPath);
			System.out.println(a.getNombre()+ " esta encerrado!");
			
			salir = true;
		}
		
		
		
	}

}
