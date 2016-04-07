package es.upm.dit.adsw.hebras;

/**
 * Reloj que escribe la hora cada segundo
 * y produce un sonido cuando se pulsa INTRO
 * 
 * @author jpuente
 * @version 24.09.2013
 */
public class Reloj {

	/**
	 * @param args -- no hay argumentos
	 */
	public static void main(String[] args) {
		Hora hora   = new Hora () ;          // crea las hebras
		Sonido sonido = new Sonido();
		hora.start();                        // ... y las arranca
		sonido.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		hora.detener();
	}

}

