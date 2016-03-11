package es.upm.dit.adsw.puentesencillo;

public class CocheNorte extends Thread{
	private int idCoche;
	private GestorPuenteSencillo unGestor;
	private long retardoInicial;

	public CocheNorte (GestorPuenteSencillo unGestor, int idCoche, long retardoInicial) {
		this.idCoche  = idCoche;
		this.unGestor = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){

		try {
			Thread.sleep(retardoInicial);
			unGestor.entrarPuente(idCoche);
			Thread.sleep(2000);
			unGestor.salirPuente(idCoche);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}

	}

} 
