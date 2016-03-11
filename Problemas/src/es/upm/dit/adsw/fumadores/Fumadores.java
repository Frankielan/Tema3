/**
 * 
 */
package es.upm.dit.adsw.fumadores;

/**
 * Problema de los fumadores
 * 
 * @author jpuente
 * @version 2014.04.08
 */
public class Fumadores {
	
	public static void main(String[] args) {
		Mesa mesa = new Mesa();	
		new Estanquero(mesa).start();
		new Fumador(1,Ingrediente.PAPEL, mesa).start();
		new Fumador(2,Ingrediente.TABACO, mesa).start();
		new Fumador(3,Ingrediente.CERILLAS, mesa).start();
	}

}
