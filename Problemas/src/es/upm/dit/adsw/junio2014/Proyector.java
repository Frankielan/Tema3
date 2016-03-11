package es.upm.dit.adsw.junio2014;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen.
 */
public class Proyector extends Thread
{
	private final Monitor monitor;
	private final int duracionProyeccion= 15000;
	private final int duracionDescanso= 5000;
	Proyector (Monitor monitor) {
		this.monitor= monitor;
		this.start();
	}
	@Override
	public void run() {
		while (true) {
			try {
				sleep(duracionDescanso);
				monitor.comienzaProyeccion();
				sleep(duracionProyeccion);
				monitor.terminaProyeccion();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	} 
}