package model.powerUp;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class ExtraLife extends PowerUp{
	
	private Timer timer=null;

	public ExtraLife(int pX, int pY) {
		super(pX, pY);
		colocarPowerUp();
	}
	
	private void colocarPowerUp() {
		timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().colocarPowerUp();
            }
        };
        timer.schedule(timerTask, 15000);
	}
	
	@Override
	public boolean is(String pType) {
		return pType=="ExtraLife" || pType=="PowerUp";
	}
	
}
