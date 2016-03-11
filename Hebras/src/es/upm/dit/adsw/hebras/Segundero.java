package es.upm.dit.adsw.hebras;

/**
 * Segundero interrumpible
 * 
 * @author jpuente
 * @version 20.09.2013
 */
public class Segundero extends Thread {

	private volatile boolean activo = true;

	public void run() {
		int segundos = 0;
		try {
			while (activo) {
				sleep(1000); // La hebra se bloquea un segundo
				segundos++;
				System.out.println("Segundos: " + segundos);
			}
		} catch (InterruptedException e) {
			System.err.println("Hebra interrumpida");
			return; // La hebra ha sido desbloqueada mediante la
		} // invocación de interrupt
		System.out.println("Terminado sin interrupción");
	}

	public void detener() {
		activo = false;
	}

}
