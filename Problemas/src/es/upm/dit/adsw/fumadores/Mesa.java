package es.upm.dit.adsw.fumadores;

import java.util.EnumSet;

/**
 * Mesa 
 * 
 * @author jpuente
 * @version 2015.04.08
 */
public class Mesa {
	
	private EnumSet<Ingrediente> ingredientes = EnumSet.noneOf(Ingrediente.class);
	
	/**
	 * Toma ingredientes de la mesa
	 * 
	 * @param id            - fumador que quiere los ingredientes
	 * @param ingredientes  - ingredientes que quiere
	 */
	public synchronized void toma(int id, EnumSet<Ingrediente> ingredientes) {
		// System.out.println("El fumador " + id + " quiere " + ingredientes + ", hay " + this.ingredientes);
		try {
			while (!this.ingredientes.containsAll(ingredientes)) {
				wait();
			}
			this.ingredientes.clear();
			notifyAll();
		} catch (InterruptedException ignored) {}
		System.out.println("El fumador " + id + " toma " + ingredientes );
	}
	
	/**
	 * Pon ingredientes en la mesa
	 * 
	 * @param ingredientes - ingredientes que se ponen
	 */
	public synchronized void pon (EnumSet<Ingrediente> ingredientes) {
		// System.out.println("El estanquero va a poner " + ingredientes + ", hay " + this.ingredientes);
		try {
			while (!this.ingredientes.isEmpty()) {
				wait();
			}
			this.ingredientes.addAll(ingredientes);
			notifyAll();
		} catch (InterruptedException ignored) {}
		System.out.println("El estanquero ha puesto " + ingredientes);
	}
	
}
