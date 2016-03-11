package es.upm.dit.adsw.rw;

import java.util.Random;

/**
 * Lector
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class Lector extends Thread {

	Gestor gestor;
	int id;
	Random random = new Random();

	public Lector(Gestor gestor, int id) {
		this.gestor = gestor;
		this.id = id;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(random.nextInt(1000));
				gestor.empiezaLeer(id);
				Thread.sleep(random.nextInt(1500));
				gestor.terminaLeer(id);
			}
		} catch (InterruptedException ie) {
			return;
		}
	}

}
