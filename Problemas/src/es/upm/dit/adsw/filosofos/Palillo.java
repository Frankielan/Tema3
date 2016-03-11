package es.upm.dit.adsw.filosofos;

/**
 * Palillo
 * 
 * @author jpuente
 * @version 30.10.2014
 */
public class Palillo {

	private boolean ocupado;
	private int id;

	public Palillo(int id) {
		ocupado = false;
		this.id = id;
	}

	/**
	 * Toma el palillo
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void toma() throws InterruptedException {
		while (ocupado)
			wait();
		System.out.println("Palillo " + id + " ocupado");
		ocupado = true;
	}

	/**
	 * Deja el palillo en la mesa
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void deja() throws InterruptedException {
		ocupado = false;
		System.out.println("Palillo " + id + " libre");
		notifyAll();
	}
}
