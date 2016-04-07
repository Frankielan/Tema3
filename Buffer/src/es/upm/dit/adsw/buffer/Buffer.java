package es.upm.dit.adsw.buffer;

/**
 * Interfaz genérica para tampones (buffers)
 * 
 * @author jpuente
 * @version 07.04.2016
 * 
 * @param <E> tipo de elementos que se envían a través del buffer
 */
public interface Buffer<E> {
	
	void enviar(E dato);
	
	E recibir();
}
