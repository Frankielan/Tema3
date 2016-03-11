package es.upm.dit.adsw.junio2013;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen.
 */
public class GestorDespegue {
	   
	private boolean      pistaOcupada      = true;
	private int          nAvionesEsperando = 0;
	private final int    tiempoAvion       = 3;
	private final int    tiempoAvioneta    = 2;
	private boolean      anteriorAvioneta  = false; 
	private Temporizador unTemporizador    = new Temporizador(this);
		
 	public synchronized void despegarAvion() throws InterruptedException {
 
 		nAvionesEsperando ++;
 		while (pistaOcupada) wait();
        nAvionesEsperando --;
 	    anteriorAvioneta = false;
   	    unTemporizador.iniciarTemporizador(tiempoAvion);
 	    pistaOcupada = true;
 	}

 	public synchronized void  despegarAvioneta() throws InterruptedException {
 	    while (pistaOcupada || (nAvionesEsperando > 0 && anteriorAvioneta)) wait();
 	    anteriorAvioneta = true;
   	    unTemporizador.iniciarTemporizador(tiempoAvioneta);
 	    pistaOcupada = true; 		
 	}
 	
 	public synchronized void  finTemporizador() throws InterruptedException {
 		pistaOcupada = false;
 		notifyAll();
 	}	
}