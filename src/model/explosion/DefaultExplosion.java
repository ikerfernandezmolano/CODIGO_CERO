package model.explosion;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class DefaultExplosion extends Explosion{
	
	public DefaultExplosion(int pX, int pY) {
		super(pX,pY);
		tiempo(pX,pY);
	}
	
	@Override
	protected void tiempo(int pX, int pY) {
		Timer timer = new Timer();
   		TimerTask timerTask = new TimerTask() {
   			@Override
   			public void run() {
   				quitarExplosion(pX,pY);
    		}		
    	};
   		timer.schedule(timerTask, 2000); 
	}

	private void quitarExplosion(int pX, int pY){
		GameModel.getGameModel().setCell("Void",pX,pY);
		GameModel.getGameModel().changeFlagStatus(GameModel.getGameModel().posBoolean("END_EXPLOSION"), true);
	}
	
}
