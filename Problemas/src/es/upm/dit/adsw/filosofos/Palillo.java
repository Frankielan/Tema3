package es.upm.dit.adsw.filosofos;

/**
 * Palillo
 * 
 * @author jpuente
 * @version 2016.04.15
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
	 */
	public synchronized void toma() {
		try {
			while (ocupado)
				wait();
		} catch (InterruptedException ignored) {
		}
		System.out.println("Palillo " + id + " ocupado");
		ocupado = true;
	}

	/**
	 * Deja el palillo en la mesa
	 */
	public synchronized void deja() {
		ocupado = false;
		System.out.println("Palillo " + id + " libre");
		notifyAll();
	}
}
