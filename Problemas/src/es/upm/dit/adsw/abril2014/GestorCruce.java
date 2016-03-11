package es.upm.dit.adsw.abril2014;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Monitor que gestiona un cruce de carreteras con semáforos
 */

public class GestorCruce {
	private boolean norteVerde = true;     // implica que el oeste está rojo
	private boolean cochePasando = false;
	public synchronized void entraNorte()
			throws InterruptedException {
		while (!norteVerde || cochePasando)
			wait();
		cochePasando = true;
	}
	public synchronized void entraOeste()
			throws InterruptedException {
		while (norteVerde || cochePasando)
			wait();
		cochePasando = true;
	}
	public synchronized void sale() {
		cochePasando = false;
		notifyAll();
	}
	public synchronized void cambiaSemaforos(){
		norteVerde = !norteVerde;
		notifyAll();
	}
}