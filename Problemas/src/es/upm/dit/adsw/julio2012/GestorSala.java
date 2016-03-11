package es.upm.dit.adsw.julio2012;

/**
 * @author Alejandro Alonso
 * @since  20150223
 * Monitor que gestiona la entrada a una sala de museo, dependiendo de la capacidad m'axima
 * que depende de la temperatura. Los jubilados tienen prioridad
 */
public class GestorSala {

	private int nPersonas = 0;
	private int nMaxPersonasNormalT = 50;
	private int nMaxPersonasAltaT   = 35;
	private int nMaxPersonas = nMaxPersonasNormalT;
	private int tUmbral = 30;
	private int nJubilados = 0;

	public synchronized void entrarSalaJubilado()
			throws InterruptedException {
		nJubilados ++;
		while (nPersonas >= nMaxPersonas) {
			wait();
		}
		nJubilados --;
        if (nJubilados == 0) notifyAll();		
		nPersonas ++;
	}

	public synchronized void entrarSala ()
			throws InterruptedException {
		while (nPersonas >= nMaxPersonas || nJubilados > 0)
		{
			// Espero si no pueden entrar m'as personas
			wait();
		}
		nPersonas++;
	}

	public synchronized void salirSala ()
			throws InterruptedException {
		if (nPersonas == nMaxPersonas) {
			notifyAll();
		}
		nPersonas--;
	}

	public synchronized void notificarTemperatura (int temperatura)
	{
		if (temperatura > tUmbral) nMaxPersonas = nMaxPersonasAltaT;
		if (temperatura < tUmbral) {
			nMaxPersonas = nMaxPersonasNormalT;
			notifyAll();
		}
	}
}