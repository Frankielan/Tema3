package es.upm.dit.adsw.buffer;

/**
 * Buffer con capacidad para varios elementos
 * 
 * @author jpuente
 * @version 07.04.2016
 */
public class BufferMultiple <E> implements Buffer<E> {

	private E[] almacen;
	private int nmax = 1;            // tamaño del buffer
	private int n = 0;               // número de elementos almacenados
	private int in = 0;
	private int out = 0;
	
	
	/**
	 * Crear un nuevo buffer
	 * 
	 * @param n tamaño del buffer
	 */
	@SuppressWarnings("unchecked")
	public BufferMultiple (int n) {
		this.nmax = n;
		this.almacen = (E[]) new Object[n];
	}

	public synchronized void enviar(E dato) {
		try {
			while (n >= nmax)
				wait(); // espera que haya sitio
		} catch (InterruptedException ignored) {
		}
		almacen[in] = dato;
		in = ++in % nmax;
		n++;
		notifyAll(); // avisa de que hay un valor
	}

	public synchronized E recibir() {
		E dato = null;
		try {
			while (n <= 0)
				wait(); // espera que haya un valor
		} catch (InterruptedException ignored) {
		}
		dato = almacen[out];
		out = ++out % nmax;
		n--;
		notifyAll(); // avisa de que hay sitio
		return dato;
	}

}
