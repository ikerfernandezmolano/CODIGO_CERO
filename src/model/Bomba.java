package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;

public class Bomba {
    private boolean activa; // 
    private long tiempoColocacion;
    private static final int TIEMPO_EXPLOSION = 2000; // 2 segundos
    private List<Bomba> bombas;

    public Bomba() {
        this.activa = true;
        this.tiempoColocacion = System.currentTimeMillis();
        this.bombas = new ArrayList<>();
    }

    public boolean haExplotado() {
        return (System.currentTimeMillis() - tiempoColocacion) >= TIEMPO_EXPLOSION;
    }
    
    public boolean isActiva() { 
    	return activa; 
    }

    public void explotar() {
        this.activa = false;
        System.out.println("La bomba ha explotado en (" + x + ", " + y + ")h");
    }
   

    public void colocarBomba(int x, int y) {
        Bomba nuevaBomba = new Bomba(x, y);
        bombas.add(nuevaBomba);
        System.out.println("La bomba ha sido colocada en (" + x + ", " + y + ")");   
    }

    private void iniciarLoop() {
        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Bomba> itr = bombas.iterator();
                while (itr.hasNext()) {
                    Bomba bomba = itr.next();
                    if (bomba.haExplotado()) {
                        bomba.explotar();
                        itr.remove();
                    }
                }
            }
        });
        timer.start();
    }
}
