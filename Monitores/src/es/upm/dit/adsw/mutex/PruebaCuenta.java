package es.upm.dit.adsw.mutex;

/**
 * Ejemplo elemental: condiciones de carrera en variables compartidas.
 * 
 * @author jpuente
 * @version 15.10.2014
 */
public class PruebaCuenta {

	/**
	 * variable compartida.
	 */
	static long cuenta = 0;

	static final long nVeces = 1000000;
	static final int nThreads = 10;

	/**
	 * Hebra que incrementa cuenta nVeces
	 */
	private static class Incrementa extends Thread {
		public void run() {
			for (long i = 0; i<nVeces; i++)
				cuenta++;    // región crítica
		}
	}

	public static void main(String[] args) {
		System.out.println(nThreads + " contadores incrementando " 
				+ "la cuenta " + nVeces +" veces cada uno" );
		Incrementa[] hebra = new Incrementa[nThreads];
		
		for (int id = 0; id < nThreads; id++) {
			hebra[id] = new Incrementa();
			hebra[id].start();
		}
		
		for (int id = 0; id < nThreads; id++) {
			try{hebra[id].join();}
			catch (InterruptedException e) {return;}
		}
		
		System.out.print("cuenta = " + cuenta);
		System.out.println("; debería ser " + nThreads*nVeces);
	}

}
