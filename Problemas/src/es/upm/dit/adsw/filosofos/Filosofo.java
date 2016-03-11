package es.upm.dit.adsw.filosofos;

import java.util.Random;

/**
 * Filósofo
 * 
 * @author jpuente
 * @version 30.10.2014
 */
public class Filosofo extends Thread {

	private int id;
	private Palillo izquierdo, derecho;

	private Random random = new Random();

	/**
	 * Constructor
	 * 
	 * @param id
	 *            : identidad del filósofp
	 * @param izquierdo
	 *            : palillo izquierdo
	 * @param derecho
	 *            : palillo derecho
	 */
	public Filosofo(int id, Palillo izquierdo, Palillo derecho) {
		this.id = id;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
	}

	@Override
	public void run() {
		System.out.println("Empieza el filósofo " + id);
		while (true) {
			try {
				System.out.println(id + " piensa");
				Thread.sleep(random.nextInt(5000));
				System.out.println(id + " coge el palillo " + id);
				izquierdo.toma();
				Thread.sleep(1000); // para facilitar el interbloqueo
				System.out.println(id + " coge el palillo " + ((id + 1) % 5));
				derecho.toma();
				System.out.println(id + " come");
				Thread.sleep(random.nextInt(2000));
				System.out.println(id + " deja el palillo " + ((id + 1) % 5));
				derecho.deja();
				System.out.println(id + " deja el palillo " + id);
				izquierdo.deja();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
			;
		}
	}

}
