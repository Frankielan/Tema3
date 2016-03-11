package es.upm.dit.adsw.hebras;

import java.util.Date;

/**
 * Escribe la hora cada 1 s - versión Runnable
 * 
 * @author jpuente
 * @version 15.10.2013
 */
public class Hora2 implements Runnable {

	@Override
	public void run()  {
		try {
			while (true) {
				Thread.sleep(1000);
				System.out.println(new Date().toString());
			}
		} catch (InterruptedException e) {
			return;            
		}
	}

}
