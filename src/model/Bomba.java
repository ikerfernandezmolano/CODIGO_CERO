package model;
import java.util.Timer;
import java.util.TimerTask;

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
   					Tablero.getTablero().explotar(x,y);
    		}		
    	};
   		timer.schedule(timerTask, 3000);
   }
    
}

