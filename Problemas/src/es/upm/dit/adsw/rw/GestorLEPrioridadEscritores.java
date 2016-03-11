package es.upm.dit.adsw.rw;

/**
 * Monitor para gestionar un conjunto de hebras lectoras y escritoras. Las
 * hebras lectoras tienen prioridad frente a las lectoras. Si hay escritores
 * continuamente entrando, los lectores nunca entrarán: situación de hambruna
 * (inanición).
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class GestorLEPrioridadEscritores implements Gestor {

	private boolean bloqueoEscritor = false;
	private boolean bloqueoLector = false;
	private int nEscritoresEsperando = 0;
	private int nLectores = 0;

	public synchronized void empiezaLeer(int idLector)
			throws InterruptedException {

		if (bloqueoEscritor || nEscritoresEsperando > 0)
			System.out.println("LLLLL La hebra lectora " + idLector
					+ " se ha bloqueado");

		while (bloqueoEscritor || nEscritoresEsperando > 0) {
			wait();
		}
		bloqueoLector = true;
		nLectores++;

		System.out.println("L>>>> La hebra lectora " + idLector
				+ " tiene permiso para leer");

	}

	public synchronized void empiezaEscribir(int idEscritor)
			throws InterruptedException {

		if (bloqueoEscritor || bloqueoLector)
			System.out.println("EEEEE La hebra escritora " + idEscritor
					+ " se ha bloqueado");

		nEscritoresEsperando++;
		while (bloqueoEscritor || bloqueoLector) {
			wait();
		}
		nEscritoresEsperando--;
		bloqueoEscritor = true;

		System.out.println("E>>>> La hebra escritora " + idEscritor
				+ " tiene permiso para escribir");

	}

	public synchronized void terminaLeer(int idLector) {
		System.out.println("L<<<< La hebra lectora " + idLector
				+ " ha terminado de leer");

		nLectores--;
		if (nLectores == 0) {
			bloqueoLector = false;
			notifyAll();
		}
	}

	public synchronized void terminaEscribir(int idEscritor) {
		System.out.println("E<<<< La hebra escritora " + idEscritor
				+ " ha terminado de escribir");

		bloqueoEscritor = false;
		notifyAll();
	}
}