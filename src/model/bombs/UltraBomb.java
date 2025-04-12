package model.bombs;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class UltraBomb extends Bomb{
private Timer timer=null;
	
	public UltraBomb(int pX, int pY) {
		super(pX,pY);
		tiempo(pX,pY);
	}
	
	protected void tiempo(int pX,int pY) {
	   	timer = new Timer();
  		TimerTask timerTask = new TimerTask() {
  			@Override
  			public void run() {
  				GameModel.getGameModel().explotar(pX, pY,17);
  			}		
  		};
  		timer.schedule(timerTask, 3000);
   }
}
