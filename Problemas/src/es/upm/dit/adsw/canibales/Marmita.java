package es.upm.dit.adsw.canibales;

/**
 * Marmita - monitor para gestionar el uso de la marmita por parte del coniero y
 * los caníbales
 * 
 * @author aalonso
 * @author jpuente
 * @version 2014.03.21
 */
public class Marmita {
	private final int max;
	private int n = 0; // Inicialmente la marmita está vacía
	private boolean primero = false;

	public Marmita(int capacidad) {
		this.max = capacidad;
	}

	public synchronized void comer(int id) {

		if (n == 0) {
			System.out.println("El canibal " + id
					+ " se para. La marmita está vacía");
		}

		if (primero && n == 0) {
			notifyAll();
			primero = false;
			System.out.println("El canibal " + id + " avisa al cocinero");
		}

		try {
			while (n == 0) {
				wait();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return;
		}

		n--;
		System.out.println("Come canibal " + id + "; quedan " + n + " raciones");
		notifyAll();
	}

	public synchronized void rellenar() {
		try {
			while (n > 0 || primero)
				wait();
		} catch (InterruptedException ie) {
			return;
		}

		System.out.println("Rellena el cocinero ");
		n = max;
		primero = true;
		notifyAll();
	}

}
