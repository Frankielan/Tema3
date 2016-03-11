package es.upm.dit.adsw.hebras;

/**
 * Temporizador construido con el segundero.
 * 
 * @author jpuente
 * @version 20.09.2013
 */
public class Temporizador {

	/**
	 * @param args
	 *            - no arguments
	 */
	public static void main(String[] args) {
		int tiempoEspera = 5; // Segundos de espera hasta terminar
		Segundero segundero = new Segundero();
		segundero.start();
		try {
			Thread.sleep(tiempoEspera * 1000);
//			segundero.interrupt(); // Fin de la espera
			segundero.detener();
		} catch (Exception e) {
			System.err.println("Error esperando ");
		}

	}

}
