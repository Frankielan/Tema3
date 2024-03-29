package es.upm.dit.adsw.trenes;

/**
 * @author Jose A. Manas
 * @version 11/2/2012
 */

import java.awt.*;
import java.util.concurrent.Semaphore;

import es.upm.dit.adsw.trenes.tramos.Tramo;
import static es.upm.dit.adsw.trenes.Enlace.*;

public class Escenario1 {

    /**
     * Metodo principal para arrancar desde consola.
     *
     * @param args No utiliza argumentos.
     */
    public static void main(String[] args) {
        String[] mapa = {
                "",
                "  - se he  h ws -",
                "  -  v  -  -  v -",
                "  - ne  h  es_w nw -",
                ""
        };
        Terreno terreno = new Terreno(mapa);
        Tramo estacion1 = terreno.get(2, 3);
        Tramo estacion2 = terreno.get(3, 3);

        Semaphore semaforo = new Semaphore(1);
        terreno.ponSemaforo(estacion1, W, semaforo);
        terreno.ponSemaforo(estacion2, E, semaforo);

        Tren tren1 = new Tren("Talgo", Color.RED);
        tren1.setVelocidad(0.4);
        terreno.ponTren(1, 2, S, tren1);

        Tren tren2 = new Tren("Expreso", Color.BLUE);
        tren2.setVelocidad(0.1);
        terreno.ponTren(4, 2, N, tren2);

        terreno.setVisible();
    }
}