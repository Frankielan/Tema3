/**
 * Prueba de acceso concurrente a un contador sincronizado
 */
package es.upm.dit.adsw.mutex;

/**
 * @author jpuente
 * @version 15.10.2014
 */
public class PruebaContadorSincronizado {
	
	static final long nVeces = 1000000;
	static final int nThreads = 10;

	/**
	 * Hebra que incrementa el contador n veces
	 */
	private static class Incrementa extends Thread {
		ContadorSincronizado contador;
		
		public Incrementa (ContadorSincronizado c) {
			contador = c;
		}
		
		public void run() {
			for (long i = 0; i<nVeces; i++)
				contador.incrementar();    // región crítica
		}
	}
	
	public static void main(String[] args) {
		ContadorSincronizado contador = new ContadorSincronizado(0);
		Incrementa[] hebra = new Incrementa[nThreads];

		System.out.println(nThreads + " contadores incrementando " 
				+ "la cuenta " + nVeces +" veces cada uno" );	
		
		for (int id = 0; id < nThreads; id++) {
			hebra[id] = new Incrementa(contador);
			hebra[id].start();
		}
		
		for (int id = 0; id < nThreads; id++) {
			try{hebra[id].join();}
			catch (InterruptedException e) {return;}
		}
		
		System.out.print("cuenta = " + contador.valor());
		System.out.println("; debería ser " + nThreads*nVeces);
	}

}
