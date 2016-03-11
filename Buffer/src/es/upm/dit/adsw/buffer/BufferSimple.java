package es.upm.dit.adsw.buffer;

/**
 * Buffer con capacidad para 1 dato
 * 
 * @author jpuente
 * @version 20.03.2013
 */
public class BufferSimple <E> implements Buffer<E> {

	private E almacen;
	private boolean lleno = false;

	public synchronized void enviar(E dato) 
			throws InterruptedException {
		while (lleno) wait();     // espera que haya sitio
		almacen = dato;
		lleno = true;
		notifyAll();              // avisa de que hay un valor
	}

	public synchronized E recibir() 
			throws InterruptedException {
		E dato = null;
		while (!lleno) wait(); // espera que haya un valor
		dato = almacen;
		lleno = false;
		notifyAll();              // avisa de que hay sitio
		return dato;
	}

}
