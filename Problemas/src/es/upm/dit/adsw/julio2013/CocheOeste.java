package es.upm.dit.adsw.julio2013;



public class CocheOeste extends Thread{
	
	private java.util.Random generador = new java.util.Random(System.currentTimeMillis());
	private int idCoche;
	private GestorGaraje unGestor;
	private long retardoInicial;
	private int  retardoMax = 10000;

	public CocheOeste (GestorGaraje unGestor, int idCoche, long retardoInicial) {
		this.idCoche        = idCoche;
		this.unGestor       = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.entraCochePorOeste(idCoche);
			Thread.sleep(generador.nextInt(retardoMax));
			unGestor.saleCoche(idCoche);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

	}
}
