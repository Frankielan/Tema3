package es.upm.dit.adsw.trenes.tramos;

import es.upm.dit.adsw.trenes.Enlace;

/**
 * @author Jose A. Manas
 * @version 1/3/2012
 */
public class RectoH_E
        extends RectoH {
    public RectoH_E(int cx, int cy) {
        super(cx, cy);
        setImagen("RectoHE_nbg.png");
    }

//    @Override
//    public Enlace mueve(Enlace entrada, double[] xy, double velocidad, int tic) {
//        double ds1 = 0.1 * velocidad;
//        double ds = ds1 * tic;
//        xy[1] = 0.5;
//        if (entrada == Enlace.E) {
//            xy[0] = 1 - ds;
//            if (ds1 * (tic - 1) > 0.5 && xy[0] <= 0.5)
//                getTren().parar();
//            return salePorW(xy, ds1);
//        } else {
//            xy[0] = ds;
//            if (ds1 * (tic - 1) < 0.5 && xy[0] >= 0.5)
//                getTren().parar();
//            return salePorE(xy, ds1);
//        }
//    }

    @Override
    public Enlace mueve(Enlace entrada, double[] xy, double velocidad) {
        double x0 = xy[0];
        Enlace salida = super.mueve(entrada, xy, velocidad);
        double x2 = xy[0];
        if (x0 < 0.5 && x2 >= 0.5) {
            getTren().parar();
            return null;
        }
        if (x0 > 0.5 && x2 <= 0.5) {
            getTren().parar();
            return null;
        }
        return salida;
    }
}
