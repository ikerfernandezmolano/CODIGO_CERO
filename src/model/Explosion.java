package model;

import java.util.Timer;
import java.util.TimerTask;

public class Explosion {
	private Timer timer=null;
	public Explosion (int pX, int pY) {	
		timer = new Timer();
   		TimerTask timerTask = new TimerTask() {
   			@Override
   			public void run() {
   				Tablero.getTablero().explotar(pX,pY,0);
    		}		
    	};
   		timer.schedule(timerTask, 2000); 
    }

}
