package es.upm.dit.adsw.buffer;

import java.util.Random;

/**
 * Consumidor. Recibe mensajes de texto.
 * 
 * @author jpuente
 * @version 15.10.2014
 */
public class Consumidor<E> implements Runnable {

	private Buffer<E> b; 
	private String id;

	private final Random random = new Random();

	public Consumidor(String id, Buffer<E> b) {
		this.id = id;
		this.b = b;
	}

	public void run() {
		try {
			while (true) { 
				String msg = b.recibir().toString(); 
				System.out.println("Consumidor " + id + " recibe " + msg);
				Thread.sleep(random.nextInt(2) * 1000);
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
