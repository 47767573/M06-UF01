package Practica2;

import java.util.Scanner;




public class Zoo {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
				
		String nombreAnimal;
		String numeroAnimal;
		String codigoAnimal="";
		
		String parentPath = "";
		
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
				
				if (valido == false) System.out.println("codigo incorrecto!");				
			}
			System.out.println("codigo incorrecto!");
			
			Animal a = new Animal (nombreAnimal, numeroAnimal, codigoAnimal);
<<<<<<< HEAD
=======
			Enjaular.guardar(a,parentPath);
			System.out.println(a.getNombre()+ " esta encerrado!");
>>>>>>> db26118b9303c9c649bb7b8c4d2073c7cf860827
			
		}
		
		
		
		
		
	}

}
