package es.upm.dit.adsw.rw;

/**
 * Interfaz con las operaciones necesarias para gestionar un conjunto de hebras
 * lectoras y escritoras
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 * 
 */
public interface Gestor {

	/**
	 * Método que invoca una hebra lectora antes de comenzar a leer
	 */
	public void empiezaLeer (int idLector) throws InterruptedException;

	/**
	 * Método que invoca una hebra lectora al terminar de leer
	 */
	public void terminaLeer (int idLector);

	/**
	 * Método que invoca una hebra escritora antes de comenzar a escribir
	 */
	public void empiezaEscribir (int idEscritor) throws InterruptedException;

	/**
	 * Método que invoca una hebra lectora al terminar de escribir
	 */
	public void terminaEscribir (int idEscritor);
}
