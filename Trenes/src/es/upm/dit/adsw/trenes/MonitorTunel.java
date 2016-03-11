package es.upm.dit.adsw.trenes;

import java.util.ArrayList;
import java.util.List;

import es.upm.dit.adsw.trenes.tramos.Tramo;

/**
 * Ejemplo de monitor de un tramo compartido.
 * Deja pasar varios trenes en la misma direccion.
 *
 * @author Jose A. Manas
 * @version 9/3/2012
 */
public class MonitorTunel
        extends Monitor {
    private final Tramo tramo1;
    private final Tramo tramo2;

    private List<Tren> circulando12 = new ArrayList<Tren>();
    private List<Tren> circulando21 = new ArrayList<Tren>();

    public MonitorTunel(Tramo tramo1, Tramo tramo2) {
        this.tramo1 = tramo1;
        this.tramo2 = tramo2;
    }

    public synchronized void entro(Tren tren, Tramo tramo, Enlace entrada) {
        if (!isMonitorizado(tramo, entrada))
            return;
        try {
            if (tramo.equals(tramo1)) {
                while (circulando21.size() > 0)
                    wait();
                circulando12.add(tren);
            }
            if (tramo.equals(tramo2)) {
                while (circulando12.size() > 0)
                    wait();
                circulando21.add(tren);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public synchronized void salgo(Tren tren, Tramo tramo, Enlace salida) {
        if (!isMonitorizado(tramo, salida))
            return;
        if (tramo.equals(tramo1)) {
            circulando21.remove(tren);
            notifyAll();
        }
        if (tramo.equals(tramo2)) {
            circulando12.remove(tren);
            notifyAll();
        }
    }
}
