package es.upm.dit.adsw.trenes;

import es.upm.dit.adsw.trenes.tramos.Tramo;

/**
 * @author Jose A. Manas
 * @version 9/3/2012
 */
public class TramoEnlace {
    private final Tramo tramo;
    private final Enlace enlace;

    public TramoEnlace(Tramo tramo, Enlace enlace) {
        this.tramo = tramo;
        this.enlace = enlace;
    }

    public Tramo getTramo() {
        return tramo;
    }

    public Enlace getEnlace() {
        return enlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TramoEnlace that = (TramoEnlace) o;
        return enlace == that.enlace && tramo.equals(that.tramo);
    }

    @Override
    public int hashCode() {
        int result = 0;
        if (tramo != null)
            result = 31 * result + tramo.hashCode();
        if (enlace != null)
            result = 31 * result + enlace.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s @ %s", enlace, tramo);
    }
}
