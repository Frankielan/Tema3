package es.upm.dit.adsw.abril2013;


/**
 * @author Alejandro Alonso
 * @since  20150223
 * Recopilado de solución de examen
 */
public class Monitor {
        private boolean ocupadoR1 = false;
        private boolean ocupadoR2 = false;
        private boolean ocupadoR3 = false;

        public synchronized void requiereR1 () throws InterruptedException {
                while (ocupadoR1) wait();
                ocupadoR1 = true;
        }

        public synchronized void requiereR2_R3() throws InterruptedException {
                while (ocupadoR2 || ocupadoR3) wait();
                ocupadoR2 = true;
                ocupadoR3 = true;
        }


        public synchronized void requiereR1_R2_R3 ()
                        throws InterruptedException {
                while (ocupadoR1 || ocupadoR2 || ocupadoR3) wait();
                ocupadoR1 = true;
                ocupadoR2 = true;
                ocupadoR3 = true;
        }

        public synchronized void liberaR1() {
                ocupadoR1 = false;
                notifyAll();
        }

        public synchronized void libreraR2_R3 () {
                ocupadoR2 = false;
                ocupadoR3 = false;
                notifyAll();
        }

        public synchronized void liberaR1_R2_R3 () {
                ocupadoR1 = false;
                ocupadoR2 = false;
                ocupadoR3 = false;
                notifyAll();
        }
}