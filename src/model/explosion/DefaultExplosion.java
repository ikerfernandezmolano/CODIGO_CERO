package model.explosion;

import java.util.Timer;
import java.util.TimerTask;

import model.Tablero;

public class DefaultExplosion extends Explosion{
	private Timer timer=null;
	
	public DefaultExplosion(int pX, int pY) {
		super(pX,pY);
		tiempo(pX,pY);
	}
	
	private void tiempo(int pX, int pY) {
		timer = new Timer();
   		TimerTask timerTask = new TimerTask() {
   			@Override
   			public void run() {
   				Tablero.getTablero().quitarExplosion(pX,pY);
    		}		
    	};
   		timer.schedule(timerTask, 2000); 
	}
}
