/**
 * Contador sincronizado
 * Se puede usar en un entorno concurrente (thread safe)
 */
package es.upm.dit.adsw.mutex;

/**
 * Contador sincronizado
 * Seguro en un entorno concurrente
 * 
 * @author jpuente
 * @version 15.10.2014
 */
public class ContadorSincronizado  {
	
	private long cuenta = 0;

	public ContadorSincronizado (long valorInicial){
		cuenta = valorInicial;
	}

	public synchronized void incrementar() {
		cuenta++;                
	}

	public synchronized long valor() {
		return cuenta;
	}
}
