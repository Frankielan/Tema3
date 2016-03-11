package es.upm.dit.adsw.fumadores;

import java.util.EnumSet;
import java.util.Random;

/**
 * Fumador
 * 
 * @author jpuente
 * @version 2015.04.08
 */
public class Fumador extends Thread {

	private int id;
	private Ingrediente ingrediente;
	private EnumSet<Ingrediente> falta;
	private Mesa mesa;

	private Random random = new Random();

	/**
	 * Constructor 
	 * 
	 * @param id            - identidad del fumador 
	 * @param ingrediente   - ingrediente  que tiene este fumador
	 * @param mesa          - mesa donde el estanquero coloca ingredientes
	 */
	public Fumador(int id, Ingrediente ingrediente, Mesa mesa) {
		this.id = id;
		this.ingrediente = ingrediente;
		this.mesa = mesa;
		this.falta = EnumSet.complementOf(EnumSet.of(ingrediente));
		
		System.out.println("creado fumador " + this.id + " con " + this.ingrediente + ", le falta " + this.falta);
	}

	public void run() {
		while (true) {
				// toma los inggredientes que le faltan
				mesa.toma(id, falta);
				// lía el cigarrillo y fuma
		}
	}

}
