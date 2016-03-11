package es.upm.dit.adsw.hebras;

/**
 * Reloj que escribe la hora cada segundo.
 * Construido con un objeto Runnable.
 * 
 * @author jpuente
 * @version 24.09.2013
 */
public class Reloj2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Hora2()).start();                    
	}

}