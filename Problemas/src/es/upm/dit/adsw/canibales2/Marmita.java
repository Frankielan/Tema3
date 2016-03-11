package es.upm.dit.adsw.canibales2;

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
	private boolean vacio = false;

	public Marmita(int capacidad) {
		this.max = capacidad;
	}

	public synchronized void comer(int id) {
		try {
			if (vacio)
				System.out.println("El canibal " + id
						+ " se para. La marmita está vacía");
			while (vacio)
				wait();
		} catch (InterruptedException ie) {
			return;
		}

		if (n == 0) {
			System.out.println("El canibal " + id + " avisa al cocinero");
			vacio = true;
			notifyAll();
			try {
				while (n == 0)
					wait();
			} catch (InterruptedException ie) {
				return;
			}
			vacio = false;
			n--;
			System.out.println("Come canibal " + id + " " + n);
			notifyAll();
		} else {
			n--;
			System.out.println("Come canibal " + id + " " + n);
		}
	}

	public synchronized void rellenar() {
		try {
			// No se deja sólo NRaciones > 0 para que sólo
			// se rellene la marmita cuando un caníbal se
			// da cuenta de que está vacia
			// Se añade NRaciones == 0 para asegurar que el
			// cocinero no se adelante al caníbal si se da mucha
			// prisa y la gestión de las colas lo permite.
			while (!vacio || n > 0)
				wait();
		} catch (InterruptedException ie) {
			return;
		}

		System.out.println("Rellena el cocinero ");
		n = max;
		notifyAll();
	}

}
