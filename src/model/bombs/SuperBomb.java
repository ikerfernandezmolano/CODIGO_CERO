package model.bombs;

import java.util.Timer;
import java.util.TimerTask;

import model.Tablero;

public class SuperBomb extends Bomb{
	private Timer timer=null;
	
	public SuperBomb(int pX, int pY) {
		super(pX,pY);
		tiempo(pX,pY);
	}
	
	protected void tiempo(int pX,int pY) {
	   	timer = new Timer();
  		TimerTask timerTask = new TimerTask() {
  			@Override
  			public void run() {
  					Tablero.getTablero().explotar(pX, pY);
  			}		
  		};
  		timer.schedule(timerTask, 3000);
   }
}
