package es.upm.dit.adsw.buffer;

/**
 * Interfaz genérica para tampones (buffers)
 * 
 * @author jpuente
 * @version 20.03.2013
 * 
 * @param <E> tipo de elementos que se envían a través del buffer
 */
public interface Buffer<E> {
	
	void enviar(E dato) 
			throws InterruptedException;
	
	E recibir() 
			throws InterruptedException;
}
