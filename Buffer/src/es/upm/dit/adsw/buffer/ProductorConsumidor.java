package es.upm.dit.adsw.buffer;

/**
 * Prueba de productor y consumidor
 * 
 * @author jpuente
 * @version 15.10.2014
 */
public class ProductorConsumidor {

	public static void main(String[] args) {
//		Buffer<String> buffer = new BufferSimple<String>();
		Buffer<String> buffer = new BufferMultiple<String>(5);
		Productor<String> productor1  = new Productor<String>("p1", buffer, 1000);
		Productor<String> productor2  = new Productor<String>("p2", buffer, 2000);
		Consumidor<String> consumidor = new Consumidor<String>("c", buffer);
		new Thread(productor1).start();
		new Thread(productor2).start();
		new Thread(consumidor).start();
	}
}
