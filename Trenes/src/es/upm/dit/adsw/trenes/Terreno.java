package es.upm.dit.adsw.trenes;

import javax.swing.*;

import es.upm.dit.adsw.trenes.tramos.*;
import es.upm.dit.adsw.trenes.ui.GUI;
import es.upm.dit.adsw.trenes.ui.PanelTrenes;

import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @author Jose A. Manas
 * @version 5/3/2012
 */
public class Terreno {
    private final Tramo[][] mapa;
    private final int dimX;
    private final int dimY;

    private final List<Tren> trenes = new ArrayList<Tren>();
    private final Map<TramoEnlace, Semaphore> semaforos = new HashMap<TramoEnlace, Semaphore>();
    private final List<Monitor> monitores = new ArrayList<Monitor>();

    private JFrame frame;
    private GUI gui;
    private PanelTrenes panelTrenes;

    public Terreno(int dx, int dy) {
        this.dimX = dx;
        this.dimY = dy;
        mapa = new Tramo[dx][dy];
    }

    public Terreno(String[] mapa) {
        this.dimY = mapa.length;
        String[][] mapa2d = new String[dimY][];
        int dx = 0;
        for (int y = 0; y < mapa.length; y++) {
            mapa2d[y] = mapa[y].trim().split("\\s+");
            dx = Math.max(dx, mapa2d[y].length);
        }
        this.dimX = dx;
        this.mapa = new Tramo[this.dimX][this.dimY];
        for (int y = 0; y < this.dimY; y++) {
            final String[] fila = mapa2d[this.dimY - 1 - y];
            if (fila.length == 0)
                continue;
            int x = 0;
            for (String s : fila) {
                if (s.length() == 0)
                    continue;

                if (match(s, "-", "_"))
                    x++;

                else {
                    if (match(s, "h"))
                        set(new RectoH(x++, y));
                    else if (match(s, "v"))
                        set(new RectoV(x++, y));

                    else if (match(s, "he"))
                        set(new RectoH_E(x++, y));
                    else if (match(s, "ve"))
                        set(new RectoV_E(x++, y));

                    else if (match(s, "c", "+"))
                        set(new Cruce(x++, y));

                    else if (match(s, "es", "se"))
                        set(new CurvaES(x++, y));
                    else if (match(s, "en", "ne"))
                        set(new CurvaEN(x++, y));
                    else if (match(s, "ws", "sw"))
                        set(new CurvaWS(x++, y));
                    else if (match(s, "wn", "nw"))
                        set(new CurvaWN(x++, y));

                    else if (match(s, "es_n", "se_n", "v_es", "v_se"))
                        set(new DesvioES_N(x++, y));
                    else if (match(s, "es_w", "se_w", "h_se", "h_es"))
                        set(new DesvioES_W(x++, y));
                    else if (match(s, "en_s", "ne_s", "v_en", "v_ne"))
                        set(new DesvioEN_S(x++, y));
                    else if (match(s, "en_w", "ne_w", "h_wn", "h_nw"))
                        set(new DesvioEN_W(x++, y));
                    else if (match(s, "ws_n", "sw_n", "v_ws", "v_sw"))
                        set(new DesvioWS_N(x++, y));
                    else if (match(s, "ws_e", "sw_e", "h_ws", "h_sw"))
                        set(new DesvioWS_E(x++, y));
                    else if (match(s, "wn_s", "nw_s", "v_wn", "v_nw"))
                        set(new DesvioWN_S(x++, y));
                    else if (match(s, "wn_e", "nw_e", "h_wn", "h_nw"))
                        set(new DesvioWN_E(x++, y));

                    else
                        throw new IllegalArgumentException("new Terreno(): no entiendo: " + s);
                }
            }
        }
    }

    private boolean match(String s, String... claves) {
        for (String clave : claves) {
            if (s.equalsIgnoreCase(clave))
                return true;
        }
        return false;
    }

    private Tramo set(Tramo tramo) {
        int cx = tramo.getCx();
        int cy = tramo.getCy();
        mapa[cx][cy] = tramo;
        return tramo;
    }

    public int getDimX() {
        return dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public Tramo get(int x, int y) {
        return mapa[x][y];
    }

    public Tramo siguiente(Tramo actual, Enlace salida) {
        try {
            int cx = actual.getCx();
            int cy = actual.getCy();
            switch (salida) {
                case N:
                    return get(cx, cy + 1);
                case S:
                    return get(cx, cy - 1);
                case E:
                    return get(cx + 1, cy);
                case W:
                    return get(cx - 1, cy);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public void ponTren(int x, int y, Enlace enlace, Tren tren) {
        if (tren == null)
            return;
        Tramo tramo = get(x, y);
        if (tramo == null)
            throw new IllegalArgumentException(
                    String.format("Terreno.ponTren: no hay via en (%d, %d)", x, y));
        if (tramo.getTren() != null && tramo.getTren() != tren)
            throw new IllegalArgumentException(
                    String.format("Terreno.ponTren: ya hay un tren en (%d, %d)", x, y));
        synchronized (trenes) {
            tren.set(this, tramo, enlace);
            trenes.add(tren);
        }
        tren.start();
    }

    public void quitaTren(Tren tren) {
        tren.setActivo(false);
        Tramo tramo = tren.getTramo();
        if (tramo != null)
            tramo.sale();
        synchronized (trenes) {
            trenes.remove(tren);
        }
    }

    public Collection<Tren> getTrenes() {
        synchronized (trenes) {
            return trenes;
        }
    }

    public void ponSemaforo(Tramo tramo, Enlace enlace, Semaphore semaforo) {
        synchronized (semaforos) {
            TramoEnlace te = new TramoEnlace(tramo, enlace);
            semaforos.put(te, semaforo);
        }
    }

    public void quitaSemaforo(Semaphore semaforo) {
        synchronized (semaforos) {
            // 1. lo busca
            TramoEnlace te = null;
            for (TramoEnlace te1 : semaforos.keySet()) {
                if (semaforos.get(te1).equals(semaforo))
                    te = te1;
            }
            // 2. si lo encuentra, lo quita
            if (te != null)
                semaforos.remove(te);
        }
    }

    public Semaphore getSemaforo(Tramo tramo, Enlace enlace) {
        if (tramo == null || enlace == null)
            return null;
        synchronized (semaforos) {
            TramoEnlace te = new TramoEnlace(tramo, enlace);
            return semaforos.get(te);
        }
    }

    public Set<TramoEnlace> getSemaforos() {
        synchronized (semaforos) {
            return semaforos.keySet();
        }
    }

    public void ponMonitor(Monitor monitor) {
        synchronized (monitores) {
            monitores.add(monitor);
        }
    }

    public void quitaMonitor(Monitor monitor) {
        synchronized (monitores) {
            monitores.remove(monitor);
        }
    }

    public Monitor getMonitor(Tramo tramo, Enlace enlace) {
        if (tramo == null || enlace == null)
            return null;
        synchronized (monitores) {
            for (Monitor monitor : monitores) {
                if (monitor.isMonitorizado(tramo, enlace))
                    return monitor;
            }
        }
        return null;
    }

    public void setVisible() {
        if (frame == null) {
            frame = new JFrame(String.format("Trenes (%s)", Version.ID));
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            gui = new GUI(frame, this);
            panelTrenes = new PanelTrenes(this);
        }
        frame.pack();
        frame.setVisible(true);
    }

    public void pintame() {
        if (gui != null)
            gui.pintame();
    }

    public void actualiza(Tren tren) {
        if (panelTrenes != null)
            panelTrenes.actualiza(tren);
    }
}
