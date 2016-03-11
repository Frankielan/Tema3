package es.upm.dit.adsw.junio2014;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen.
 */
public class Monitor {

	private int N= 10;
	private int numVisitantes;
	private boolean proyectando;
	Monitor() {
		numVisitantes= 0;
		proyectando= false;
	}
	public synchronized void comienzaProyeccion() {
		proyectando= true;
	}
	public synchronized void terminaProyeccion() {
		proyectando= false;
		numVisitantes= 0;
		notifyAll();
	}
	public synchronized void accederASala()   {
		while ( proyectando || numVisitantes >= N)
		{
			try { wait();
			} catch (InterruptedException e) {
			} 
		}
		numVisitantes++;
	}
}