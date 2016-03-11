package es.upm.dit.adsw.puentesencillo;

/**
 * @author Alejandro Alonso
 * @date 20150323
 *
 * Este monitor trata de gestionar el acceso a un puente. El puente
 * s�lo tiene cabida para un coche, no importa en qu� direcci�n vaya. 
 * Para entrar o salir del puente se emplean los m�todos de la clase.
 */

public class GestorPuenteSencillo {

	/**
	 * Indica si hay alg�n coche en el puente
	 */
	private boolean hayCocheEnPuente = false;

	/**
	 * Este m�todo lo invocan los coche cuando quieren entrar en el puente.
	 * Si el puente est� ocupado, se bloquean.
	 * 
	 * @param idCoche Este par�metro indica el identificador del 
	 * coche que realiza la operaci�n. S�lo se usa para generar trazas
	 * 
	 * @throws InterruptedException Esta excepci�n se eleva si se ejecuta 
	 * el m�todo "interrupt" de una hebra mientras est� bloqueada. En este
	 * caso, la excepci�n no se trata localmente y se delega en la hebra 
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
	 * Este m�todo lo invocan los coches al salir del puente.
	 * 
	 * @param idCoche Este par�metro indica el identificador del 
	 * coche que realiza la operaci�n. S�lo se usa para generar trazas
	 */
	public synchronized void salirPuente(int idCoche){
		hayCocheEnPuente = false;
		// En este caso es suficiente con poner notify, ya que
		// s�lo hay una condici�n por la que todos las hebras se 
		// bloquean. Si se pone "notifyAll" el c�digo ser�a correcto, 
		// pero m�s ineficiente
		notify();

		System.out.println("<<<< El coche " + idCoche + 
				" sale del puente.");		
	}
}
