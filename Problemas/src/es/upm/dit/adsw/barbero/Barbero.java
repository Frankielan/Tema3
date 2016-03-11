package es.upm.dit.adsw.barbero;

import java.util.Random;

/**
 * Barbero
 * 
 * @author jpuente
 * @version 30.10.2014
 */
public class Barbero extends Thread {

	private Barberia barberia;

	private Random random = new Random();

	/**
	 * Constructor
	 * 
	 * @param barberia
	 */
	public Barbero(Barberia barberia) {
		this.barberia = barberia;
	}

	@Override
	public void run() {
		while (true) {
			try {
				barberia.esperaCliente();
				// corta el pelo
				Thread.sleep(random.nextInt(2000));
				barberia.acabaCorte();
				// descansa un poco
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}

	}
}
