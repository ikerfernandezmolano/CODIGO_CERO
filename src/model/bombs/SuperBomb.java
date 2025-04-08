package model.bombs;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

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
  				GameModel.getGameModel().explotar(pX, pY,1);
  			}		
  		};
  		timer.schedule(timerTask, 3000);
   }
	@Override
	public boolean mata() {
		return false;
	}
}
