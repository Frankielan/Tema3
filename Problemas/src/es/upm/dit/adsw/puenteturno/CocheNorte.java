package es.upm.dit.adsw.puenteturno;

/**
 * @author Alejandro Alonso
 * @since 20150323
**/
public class CocheNorte extends Thread{
	private int idCoche;
	private GestorPuenteTurnos unGestor;
	private long retardoInicial;
	
	public CocheNorte (GestorPuenteTurnos unGestor, int idCoche, long retardoInicial) {
		this.idCoche  = idCoche;
		this.unGestor = unGestor;
		this.retardoInicial = retardoInicial;
		this.start();
	}

	public void run(){
	
		try {
			Thread.sleep(retardoInicial);
			unGestor.entrarNorte(idCoche);
			Thread.sleep(2000);
			unGestor.salirPuente(idCoche);
		} catch (InterruptedException e) {
			// No hay tratamiento. Simplemente se termina la hebra
		}
		
	}
	
}
