package es.upm.dit.adsw.trenes;

/**
 * @author Jose A. Manas
 * @version 11/2/2012
 */

import java.awt.*;

import es.upm.dit.adsw.trenes.tramos.Tramo;

public class Escenario4 {

    /**
     * Metodo principal para arrancar desde consola.
     *
     * @param args No utiliza argumentos.
     */
    public static void main(String[] args) {
        String[] mapa = new String[]{
                "- - se h h h ws",
                "- - v - - - v",
                "- se ne_w h h ws_e wn",
                "- v - - - v",
                "- ne h h h wn",
                ""
        };
        Terreno terreno = new Terreno(mapa);

        Tramo agujas01 = terreno.get(2, 3);
        Tramo agujas02 = terreno.get(5, 3);

        agujas01.setRecto();
        agujas02.setRecto();

        Monitor tunel = new MonitorTunel(agujas01, agujas02);
        tunel.monitoriza(agujas01, Enlace.N, Enlace.W);
        tunel.monitoriza(agujas02, Enlace.S, Enlace.E);
        terreno.ponMonitor(tunel);

        Tren tren11 = new Tren("Talgo 1", Color.RED);
        tren11.setVelocidad(0.9);
        terreno.ponTren(1, 2, Enlace.S, tren11);

        Tren tren12 = new Tren("Talgo 2", Color.ORANGE);
        tren12.setVelocidad(0.9);
        terreno.ponTren(2, 1, Enlace.E, tren12);

        Tren tren13 = new Tren("Talgo 3", Color.GREEN);
        tren13.setVelocidad(0.9);
        terreno.ponTren(4, 1, Enlace.E, tren13);

        Tren tren2 = new Tren("Expreso", Color.BLUE);
        tren2.setVelocidad(0.25);
        terreno.ponTren(5, 2, Enlace.S, tren2);

        terreno.setVisible();
    }
}