package es.upm.dit.adsw.barbero;

/**
 * Monitor que gestiona la barbería
 * 
 * @author jpuente
 * @version 30.10.2014
 */
public class Barberia {

	private int nSillasEspera;
	private int nSillasEsperaOcupadas = 0;
	private boolean sillaBarberoOcupada = false;
	private boolean finCorte = false;
	private boolean barberoDormido = false;

	/**
	 * Constructor
	 * 
	 */
	public Barberia(int nSillasEspera) {
		this.nSillasEspera = nSillasEspera;
	}

	/**
	 * Entra un cliente
	 * 
	 * @param cliente
	 * @return true si se ha cortado el pelo
	 * @throws InterruptedException
	 */
	public synchronized boolean entra(int cliente) throws InterruptedException {

		if (nSillasEsperaOcupadas == nSillasEspera) {
			System.out.println("---- El cliente " + cliente
					+ " se va sin cortarse el pelo");
			return false;
		}

		nSillasEsperaOcupadas++;
		System.out.println("---- El cliente " + cliente
				+ " se sienta en la silla de espera");
		while (sillaBarberoOcupada) {
			wait();
		}

		// pasa a la silla del barbero
		nSillasEsperaOcupadas--;
		sillaBarberoOcupada = true;
		finCorte = false;

		if (barberoDormido) {
			System.out.println("---- El cliente " + cliente
					+ " despierta al barbero");
			notifyAll();
		}

		System.out.println("---- El cliente " + cliente
				+ " está en la silla de barbero");
		while (!finCorte) {
			wait();
		}

		// termina y se levanta
		sillaBarberoOcupada = false;
		notifyAll();

		System.out.println("---- El cliente " + cliente
				+ " se va con el pelo cortado");
		return true;
	}

	/**
	 * El barbero espera que llegue un cliente. Le corta el pelo fuera del
	 * monitor.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void esperaCliente() throws InterruptedException {

		barberoDormido = true;
		while (!sillaBarberoOcupada) {
			System.out.println("++++ El barbero está esperando un cliente");
			wait();
		}
		barberoDormido = false;
		System.out.println("++++ El barbero está cortando el pelo al cliente");
	}

	/**
	 * El barbero acaba de cortar el pelo al cliente.
	 */
	public synchronized void acabaCorte() {
		finCorte = true;
		System.out.println("++++ El barbero termina de cortar el pelo al cliente");
		notifyAll();
	}

}