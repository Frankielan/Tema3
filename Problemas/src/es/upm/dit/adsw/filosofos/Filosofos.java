package es.upm.dit.adsw.filosofos;

/**
 * Cinco filósofos 
 * 
 * @author jpuente
 * @version 30.10.2014
 */
public class Filosofos {

    public static void main (String[] args) {
        System.out.println("Problema de los filosofos");
        
        Palillo  P[] = new Palillo[5];
        Filosofo F[] = new Filosofo[5];        
        for (int i = 0; i < 5; i++ ) {
            P[i] = new Palillo(i);
        }    
        for (int i = 0; i < 5; i++ ) {
            System.out.println("El filosofo " + i + 
                " usa los palillos " + i + " y " + ((i+1)%5) ); 
            F[i] = new Filosofo(i, P[i], P[(i+1)%5]);
            F[i].start();
        }
    }
}
