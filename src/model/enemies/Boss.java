package model.enemies;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;
import model.TimerModelTool;

public class Boss extends Enemy{
	private int health=5;
	
	public Boss(int pX,int pY) {
		super(pX,pY);
		moverse(pX,pY);
		TimerModelTool.getTimerModelTool().bossBombs(pX, pY);
	}

	@Override
	public int getId() {
		return 31;
	}
	
	@Override
	public boolean is(String pType) {
		return pType=="Boss" || pType=="Enemy";
	}
	
	public void decreaseHealth() {
		health--;
		if(health==0) TimerModelTool.getTimerModelTool().stop(2);
	}

	@Override
	protected void moverse(int pX, int pY) {
		timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().moverseBoss(pX, pY);
            }
        };
        timer.schedule(timerTask, 8000);
	}
}
