package Practica2;

import java.io.Serializable;


public class Animal implements Serializable{

	private String nombre = "sin nombre";
	private String numero = "descatalogado";
	private String codi = "";
	
	
	
	public Animal() {}
	
	public Animal(String nombre, String numero, String codi) {	
		this.nombre = nombre;
		this.numero = numero;
		this.codi = codi;			
	}
		
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getNumero() {
		return numero;
	}

	public String getCodi() {
		return codi;
	}
	
	//{"EX","EW","CR","EN","VU","NT","LC"};
	public static boolean esCodigoValido (String codigos){
		String codigo = codigos.toLowerCase();
		if (codigo.equals("ex")) return true; 			//Extinto
		else if (codigo.equals("ew")) return true;		//Extinto en estado silvestre
		else if (codigo.equals("cr")) return true;		//Peligro critico
		else if (codigo.equals("en")) return true;		//En peligro
		else if (codigo.equals("vu")) return true;		//Vulnerable
		else if (codigo.equals("nt")) return true;		//Casi amenazado
		else if (codigo.equals("lc")) return true;		//Preocupacion menor
		else return false;
	}
	
	public void msgVal(boolean resultado){
		if (resultado == false) System.out.println("incorrecto!"); 		
	}

	
	

}
