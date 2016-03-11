package es.upm.dit.adsw.rw;

/**
 * Programa de prueba de lectores y escritores
 * 
 * @author aalonso
 * @author jpuente
 * @version 04.11.2014
 */
public class PruebaLE {

	public static void main(String[] args) {

		final int nLectores = 10;
		final int nEscritores = 8;
//		Gestor gestor = new GestorLE();
//		Gestor gestor = new GestorLEPrioridadEscritores();
		Gestor gestor = new GestorLEEquitativo();
		Lector[] lector = new Lector[nLectores];
		Escritor[] escritor = new Escritor[nEscritores];

		for (int i = 0; i < nEscritores; i++) {
			escritor[i] = new Escritor(gestor, i);
			escritor[i].start();
		}
		for (int i = 0; i < nLectores; i++) {
			lector[i] = new Lector(gestor, i);
			lector[i].start();
		}
	}
}
