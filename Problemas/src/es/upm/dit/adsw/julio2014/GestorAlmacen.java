package es.upm.dit.adsw.julio2014;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen.
 */
public class GestorAlmacen {
	private int cantidadAlmacen = 0;
	private boolean peticionPendiente = false;
	public synchronized void solicitarPiezas(int cantidadPiezas)
			throws InterruptedException {
		while (peticionPendiente) wait();
		peticionPendiente = true;
		while (cantidadAlmacen < cantidadPiezas) wait();
		cantidadAlmacen = cantidadAlmacen - cantidadPiezas;
		peticionPendiente = false;
		notifyAll();
	}
	public synchronized void agregarPiezas(int cantidadPiezas)
			throws InterruptedException {
		cantidadAlmacen = cantidadAlmacen + cantidadPiezas;
		notifyAll();
	}
}