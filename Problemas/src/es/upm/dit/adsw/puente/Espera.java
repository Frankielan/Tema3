package es.upm.dit.adsw.puente;

import java.util.Random;

/**
 * Rutinas auxiliares para suspender hebras.
 *
 * @author jose a. manas
 * @author juan a. de lapuente
 * @version 2016.04.08
 */
public class Espera {
    private static final Random RANDOM = new Random();

    /**
     * Duerme el tiempo indicado.
     *
     * @param ms milisegundos.
     */
    public static void retardo(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Duerme un periodo aleatorio entre los limites indicados.
     *
     * @param min milisegundos minimos.
     * @param max milisegundos maximos.
     */
    public static void aleatoria(int min, int max) {
        try {
            Thread.sleep(min + RANDOM.nextInt(max - min));
        } catch (InterruptedException ignored) {
        }
    }
}
