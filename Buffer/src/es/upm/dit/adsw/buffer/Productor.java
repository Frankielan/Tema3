package es.upm.dit.adsw.buffer;

import java.util.Random;

/**
 * Productor que envía mensajes de texto.
 * 
 * @author jpuente
 * @version 15.10.2014
 */
public class Productor<E> implements Runnable {

	private Buffer<E> b; 
	private String id;
	private int n = 0;

	private final Random random = new Random();

	public Productor(String id, Buffer<E> b, int n0) {
		this.id = id;
		this.b = b;
		this.n = n0;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while (true) { 
			try {
				E msg = (E) String.valueOf(n++);
				b.enviar(msg);
				System.out.println("Productor " + id + " envía " + msg);
				Thread.sleep(random.nextInt(5) * 1000);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
}
