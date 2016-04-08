package es.upm.dit.adsw.puente;

/**
 * Simula un coche que llega al puente
 * 
 * @author jpuente
 * @version 2016.04.08
 */
public class Coche implements Runnable {
	
	private int id;
	private Direccion direccion;
	private Puente puente;
	
	public Coche(int id, Direccion d, Puente p) {
		this.id = id;
		this.direccion = d;
		this.puente = p;
	}

	@Override
	public void run() {
		System.out.println("llega " + id + " hacia el " + direccion);
		puente.entra(id, direccion);
		System.out.println("entra " + id + " hacia el " + direccion);
		Espera.aleatoria(2000,3000);
		puente.sale(id, direccion);
		System.out.println("sale  " + id + " hacia el " + direccion);
	}

}
