package es.upm.dit.adsw.puente;

/**
 * Dirección de los coches
 * 
 * @author jpuente
 * @version 2016.04.08
 */
public enum Direccion {
	NORTE, SUR;
	
	/**
     * Dada una direccion, devuelve la opuesta.
     * Por ejemplo, lo opuesto al NORTE es el SUR.
     *
     * @return direccion opuesta.
     */
    public Direccion opuesta() {
        switch (this) {
            case NORTE:
                return SUR;
            case SUR:
                return NORTE;
        }
        return this;
    }
	
	 /**
     * Elige aleatoriamente.
     * @return una dirección.
     */
    public static Direccion aleatoria() {
        Direccion[] values = values();
        int random = (int) (Math.random() * values.length);
        return values[random];
    }

}
