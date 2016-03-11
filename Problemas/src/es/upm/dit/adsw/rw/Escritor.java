package es.upm.dit.adsw.rw;

import java.util.Random;

/**
 * Escritor
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class Escritor extends Thread {

	Gestor gestor;
	int id;
	Random random = new Random();

	public Escritor(Gestor gestor, int id) {
		this.gestor = gestor;
		this.id = id;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(random.nextInt(6000));
				gestor.empiezaEscribir(id);
				Thread.sleep(random.nextInt(3000));
				gestor.terminaEscribir(id);
			}
		} catch (InterruptedException ie) {
			return;
		}
	}

}
