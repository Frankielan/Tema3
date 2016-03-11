package es.upm.dit.adsw.trenes;

import java.util.HashSet;
import java.util.Set;

import es.upm.dit.adsw.trenes.tramos.Tramo;

/**
 * @author Jose A. Manas
 * @version 9/3/2012
 */
public abstract class Monitor {
    private Set<TramoEnlace> enlacesMonitorizados = new HashSet<TramoEnlace>();

    /**
     * Marca uno o más enlaces a monitorizar.
     * @param tramo tramo que se monitoriza.
     * @param enlaces enlace(s) que se monitoriza(n).
     */
    public final void monitoriza(Tramo tramo, Enlace... enlaces) {
        for (Enlace enlace : enlaces) {
            TramoEnlace te = new TramoEnlace(tramo, enlace);
            enlacesMonitorizados.add(te);
        }
    }

    /**
     *
     * @param tramo tramo por el que preguntamos.
     * @param enlace enlace del tramo por el que preguntamos.
     * @return nos dice si el enlace está monitorizado.
     */
    public final boolean isMonitorizado(Tramo tramo, Enlace enlace) {
        TramoEnlace te = new TramoEnlace(tramo, enlace);
        return enlacesMonitorizados.contains(te);
    }

    /**
     * Se llama este metodo cuando entra un tren en el tramo por el enlace indicado.
     * El usuario debe programar lo que haya que hacer.
     *
     * @param tren tren que entra.
     * @param tramo tramo en el que entra.
     * @param entrada enlace por el que entra.
     */
    public abstract void entro(Tren tren, Tramo tramo, Enlace entrada);

    /**
     * Se llama este metodo cuando un tren sale del tramo por el enlace indicado.
     * El usuario debe programar lo que haya que hacer.
     *
     * @param tren tren que sale.
     * @param tramo tramo en el que sale.
     * @param salida enlace por el que sale.
     */
    public abstract void salgo(Tren tren, Tramo tramo, Enlace salida);
}
