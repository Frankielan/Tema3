package es.upm.dit.adsw.trenes.tramos;

import es.upm.dit.adsw.trenes.Enlace;

/**
 * @author Jose A. Manas
 * @version 1/3/2012
 */
public class RectoV_E
        extends RectoV {
    public RectoV_E(int cx, int cy) {
        super(cx, cy);
        setImagen("RectoVE_nbg.png");
    }

//    @Override
//    public Enlace mueve(Enlace entrada, double[] xy, double velocidad, int tic) {
//        double ds1 = 0.1 * velocidad;
//        double ds = ds1 * tic;
//        xy[0] = 0.5;
//        if (entrada == Enlace.N) {
//            xy[1] = 1 - ds;
//            if (ds1 * (tic - 1) > 0.5 && xy[1] <= 0.5)
//                getTren().parar();
//            return salePorS(xy, ds1);
//        } else {
//            xy[1] = ds;
//            if (ds1 * (tic - 1) < 0.5 && xy[1] >= 0.5)
//                getTren().parar();
//            return salePorN(xy, ds1);
//        }
//    }

    @Override
    public Enlace mueve(Enlace entrada, double[] xy, double velocidad) {
        double y0 = xy[1];
        Enlace salida = super.mueve(entrada, xy, velocidad);
        double y2 = xy[1];
        if (y0 < 0.5 && y2 >= 0.5) {
            getTren().parar();
            return null;
        }
        if (y0 > 0.5 && y2 <= 0.5) {
            getTren().parar();
            return null;
        }
        return salida;
    }
}
