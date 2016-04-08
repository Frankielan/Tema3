package es.upm.dit.adsw.puente;

public class PuenteTest {
		
	/**
	 * Número total de coches que pasan
	 */
	private static final int N = 10;

	public static void main(String[] args) {
		
		Puente puente = new Puente();
		
		for (int i = 0; i < N; i++) {
			Espera.aleatoria(1000, 2000);
			new Thread(new Coche(i, Direccion.aleatoria(), puente)).start();
		}
		
	}

}
