
package es.upm.dit.adsw.mutex;

/**
 * Contador simple
 * No es seguro en un entorno concurrente

 * @author jpuente
 * @version 15.10.2014
 */
public class Contador {

	private long cuenta = 0;

	public Contador(long valorInicial){
		cuenta = valorInicial;
	}

	public void incrementar() {
		cuenta++;                
	}

	public long valor() {
		return cuenta;
	}
}
