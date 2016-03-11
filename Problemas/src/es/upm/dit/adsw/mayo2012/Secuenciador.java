package es.upm.dit.adsw.mayo2012;


/**
 * @author Alejandro Alonso
 * @since 20150323
 * 
 * Solución de problema de examen. 
**/

public class Secuenciador {

        private int numero = 0;

        public synchronized int siguiente(){
                numero++;
                notifyAll();
                return numero;
        }

        public synchronized void esperarPar(){
                while (numero%2 != 0) {
                        try {
                                wait();
                        } catch (InterruptedException e) { }
                }
        }

        public synchronized void esperarImpar(){

            while (numero%2 == 0) {
                    try {
                            wait();
                    } catch (InterruptedException e) { }
            }
    }
}