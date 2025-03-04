package model;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Bomba {
    private int x,y;
    private Timer timer = null;

    public Bomba(int pX, int pY) {
    	this.x=pX;
    	this.y=pY;
        timer = new Timer();
   		TimerTask timerTask = new TimerTask() {
   			@Override
   			public void run() {
   				Tablero.getTablero().explotar(x,y,6);
    		}		
    	};
   		timer.schedule(timerTask, 3000);
   }
    
}
