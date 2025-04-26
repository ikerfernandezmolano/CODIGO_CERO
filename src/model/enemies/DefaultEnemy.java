package model.enemies;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class DefaultEnemy extends Enemy{	
	public DefaultEnemy(int pX, int pY) {
		super();
		moverse(pX, pY);
	}
	
	@Override
	public int getId() {
		return 3;
	}
	
	@Override
	protected void moverse(int pX, int pY) {
		timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().moverseEnemigo(pX, pY);
            }
        };
        timer.schedule(timerTask, 1000);
	}
}
