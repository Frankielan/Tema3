package es.upm.dit.adsw.puentesencillo;

/**
 * @author Alejandro Alonso
 * @date 20150323
 *
 * Este monitor trata de gestionar el acceso a un puente. El puente
 * sólo tiene cabida para un coche, no importa en qué dirección vaya. 
 * Para entrar o salir del puente se emplean los métodos de la clase.
 */

public class GestorPuenteSencillo {

	/**
	 * Indica si hay algún coche en el puente
	 */
	private boolean hayCocheEnPuente = false;

	/**
	 * Este método lo invocan los coche cuando quieren entrar en el puente.
	 * Si el puente está ocupado, se bloquean.
	 * 
	 * @param idCoche Este parámetro indica el identificador del 
	 * coche que realiza la operación. Sólo se usa para generar trazas
	 * 
	 * @throws InterruptedException Esta excepción se eleva si se ejecuta 
	 * el método "interrupt" de una hebra mientras está bloqueada. En este
	 * caso, la excepción no se trata localmente y se delega en la hebra 
	 * su tratamiento 
	 */
	public synchronized void entrarPuente(int idCoche) throws InterruptedException {

		if (hayCocheEnPuente) {
			System.out.println("---- El coche " + idCoche + 
					" se bloquea al intentar entrar en el puente.");
		}

		while(hayCocheEnPuente) wait();	

		System.out.println(">>>> El coche " + idCoche + 
				" entra en el puente.");

		hayCocheEnPuente = true;
	}

	/**
	 * Este método lo invocan los coches al salir del puente.
	 * 
	 * @param idCoche Este parámetro indica el identificador del 
	 * coche que realiza la operación. Sólo se usa para generar trazas
	 */
	public synchronized void salirPuente(int idCoche){
		hayCocheEnPuente = false;
		// En este caso es suficiente con poner notify, ya que
		// sólo hay una condición por la que todos las hebras se 
		// bloquean. Si se pone "notifyAll" el código sería correcto, 
		// pero menos eficiente
		notify();

		System.out.println("<<<< El coche " + idCoche + 
				" sale del puente.");		
	}
}
