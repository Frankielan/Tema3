package es.upm.dit.adsw.canibales;

/**
 * Problema de los caníbales
 * 
 * @author aalonso
 * @author jpuente
 * @version 2014.03.21
 */
public class Tribu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		final int nRacionesMax = 4;
		final int tamanoTribu  = 5;
		
		Marmita marmita = new Marmita(nRacionesMax);
		
		Cocinero cocinero = new Cocinero (marmita);
		cocinero.start();
		
		Canibal[] canibales  = new Canibal[tamanoTribu];		
		for (int i = 0; i < tamanoTribu; i++){
			try{
				Thread.sleep(1000);
				canibales[i] = new Canibal(marmita, i);
				canibales[i].start();
			}catch(InterruptedException ie){}
		}
	}

}
