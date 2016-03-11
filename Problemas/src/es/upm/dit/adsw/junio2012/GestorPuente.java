package es.upm.dit.adsw.junio2012;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Monitor que gestiona la entrada a un puente de coches
 * por sus dos extremos (norte y sur). Dentro el puente s<97>lo puede
 * haber un coche. Si hay coches esperando en sus dos extremos, entra
 * uno por el extremos con m<87>s coches esperando. Si intenta entrar una
 * ambulancia, tendr<87> prioridad. No pueden intentar dos ambulancias
 * simultaneamente.
 */
public class GestorPuente {

	/** Indica si hay un coche dentro del puente 
	 */
	private boolean hayCocheEnPuente = false;

	/** Indica en n'umero de coches que est'an esperando
	 * para entrar en el puente por el norte
	 */
	private int nCochesNorte = 0;

	/** Indica en n'umero de coches que est'an esperando
	 * para entrar en el puente por el sur
	 */
	private int nCochesSur   = 0;

	/** Indica si hay una ambulancia esperando.
	 */
	private boolean hayAmbulancia = false;

	/** M'etodo que ejecutan los coches que quiere entrar por el norte.
	 * El coche entrar<87> en el puente cuando est'e vac'io, no haya m'as
	 * coches esperando en el sur y no haya ambulancias.
	 * @throws InterruptedException Esta excepci'on se eleva
	 * cuando se interrumpe a la hebra mientra est'a esperando
	 */
	public synchronized void entrarNorte()
			throws InterruptedException {

		nCochesNorte ++;
		while (hayCocheEnPuente ||
				hayAmbulancia || nCochesNorte < nCochesSur) wait();
		hayCocheEnPuente = true;
		nCochesNorte --;
	}


	/** M'etodo que ejecutan los coches que quiere entrar por el sur.
	 * El coche entrar<87> en el puente cuando est'e vac'io, no haya m'as
	 * coches esperando en el sur y no haya ambulancias.
	 * @throws InterruptedException Esta excepci'on se eleva
	 * cuando se interrumpe a la hebra mientra est'a esperando
	 */
	public synchronized void  entrarSur()
			throws InterruptedException {
		nCochesSur ++;
		while (hayCocheEnPuente ||
				hayAmbulancia || nCochesSur < nCochesNorte) wait();
		hayCocheEnPuente = true;
		nCochesSur --;
	}

	/** M'etodo que ejecutan las ambulancias que quieran entrar al puente.
	 * La ambulancia entra cuendo el puente quede libre. No intentan entrar
	 * simultaneamente al puente.
	 * @throws InterruptedException Esta excepci'on se eleva
	 * cuando se interrumpe a la hebra mientra est'a esperando
	 */
	public synchronized void  entrarAmbulancia()
			throws InterruptedException {
		hayAmbulancia = true;
		while (hayCocheEnPuente) wait();
		hayCocheEnPuente = true;
		hayAmbulancia = false;
	}


	/** M'etodo que invoca el coche que est'a en el puente al salir
	 * del mismo
	 */
	public synchronized void salirPuente() {
		hayCocheEnPuente = false;
		notifyAll();
	}
}