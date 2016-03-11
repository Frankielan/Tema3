package es.upm.dit.adsw.barbero;

import java.util.Random;

/**
 * Cliente
 * 
 * @author jpuente
 * @version 30.10.2014
 */
public class Cliente extends Thread {

	private Barberia barberia;
	private int id;
	private boolean cortado = false;

	private Random random = new Random();

	/**
	 * Constructor
	 * 
	 * @param barberia
	 * @param id
	 */
	public Cliente(Barberia barberia, int id) {
		this.barberia = barberia;
		this.id = id;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(random.nextInt(2000));
				cortado = barberia.entra(id);
				if (cortado) {
					// espera hasta que le crezca el pelo
					Thread.sleep(2000);
				} else {
					// espera y lo vuelve a intentar
					Thread.sleep(random.nextInt(500));
				}
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}