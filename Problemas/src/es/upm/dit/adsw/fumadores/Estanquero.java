/**
 * 
 */
package es.upm.dit.adsw.fumadores;

import java.util.EnumSet;
import java.util.Random;

/**
 * Estanquero
 * 
 * @author jpuente
 * @version 2015.04.08
 */
public class Estanquero extends Thread {

	private Mesa mesa;
	private Random random = new Random();
	private static Ingrediente[] ingredientes = Ingrediente.values();

	/**
	 * Constructor
	 * 
	 * @param mesa
	 */
	public Estanquero(Mesa mesa) {
		this.mesa = mesa;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(random.nextInt(5000));
				int i = random.nextInt(ingredientes.length);
				int j = (i + 1) % ingredientes.length;
				mesa.pon(EnumSet.of(ingredientes[i], ingredientes[j]));
			} catch (InterruptedException ignored) {
			}
		}
	}

}
