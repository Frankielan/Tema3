package es.upm.dit.adsw.puente;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Gestor de acceso a un puente
 * 
 * @author jpuente
 * @version 2016.04.08
 */
public class Puente {
	
	private Boolean ocupado = false;
	
	/**
	 * turno para pasar
	 */
	private Direccion turno = Direccion.NORTE;
	
	/**
	 * hay una cola para cada dirección
	 */
	private EnumMap<Direccion, List<Integer>> cola 
		= new EnumMap<Direccion, List<Integer>>(Direccion.class);
	
	public Puente () {
		for (Direccion d : Direccion.values()) {
			cola.put(d, new ArrayList<Integer>());
		}
	}
	
	public synchronized void entra(int id, Direccion d) {
		cola.get(d).add(id);
		System.out.println("turno " + turno + " --- cola: " + cola);
		try {
			while (ocupado || (d != turno && !cola.get(d.opuesta()).isEmpty())
					|| id != cola.get(d).get(0))
				wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
		cola.get(d).remove(0);
		ocupado = true;
		turno = turno.opuesta();
	}
	
	public synchronized void sale(int id, Direccion d) {
		ocupado = false;
		notifyAll();
	}

}
