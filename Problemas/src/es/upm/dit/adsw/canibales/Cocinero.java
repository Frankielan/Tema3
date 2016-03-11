package es.upm.dit.adsw.canibales;

import java.util.Random;

/**
 * Cocinero
 * 
 * @author aalonso
 * @author jpuente
 * @version 2014.03.21
 */
public class Cocinero extends Thread {

	private Marmita marmita;

	private Random random = new Random();

	public Cocinero(Marmita marmita) {
		this.marmita = marmita;
	}

	public void run() {
		while (true) {
			try {
				marmita.rellenar();
				Thread.sleep(random.nextInt(1000));
			} catch (InterruptedException ie) {
				return;
			}
		}
	}
	
}
