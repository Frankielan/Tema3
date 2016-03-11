package es.upm.dit.adsw.hebras;

import java.util.Date;

/**
 * Escribe la hora cada 1 s
 * 
 * @author jpuente
 * @version 20130924
 */
public class Hora extends Thread {

	@Override
	public void run()  {              /* código concurrente */
		try {
			while (true) {
				sleep(1000);           /* esperar 1000 ms */
				System.out.println(new Date().toString());
			}
		} catch (InterruptedException e) {
			return;                  /* terminar esta hebra */
		} 
	}
}
