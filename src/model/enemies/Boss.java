package model.enemies;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;
import model.TimerModelTool;

public class Boss extends Enemy{
	
	public Boss(int pX,int pY) {
		super();
		moverse(pX,pY);
		TimerModelTool.getTimerModelTool().bossBombs(pX, pY);
	}

	@Override
	public int getId() {
		return 31;
	}
	
	@Override
	public boolean is(String pType) {
		return pType.equals("Boss") || pType.equals("Enemy");
	}

	@Override
	protected void moverse(int pX, int pY) {
		Timer timer=getTimer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                moverseBoss(pX, pY);
            }
        };
        timer.schedule(timerTask, 8200);
	}
//
	private void moverseBoss(int pX, int pY) {
		Random r=new Random();
		boolean moved=false;
		while(!moved) {
			int x=r.nextInt(0,GameModel.getGameModel().getSize("WIDTH"));
			int y=r.nextInt(0,GameModel.getGameModel().getSize("HEIGHT"));
			if(!GameModel.getGameModel().isPosition("Bomberman",x,y)) {
				moved=true;
				GameModel.getGameModel().setCell("Boss",x,y);
				GameModel.getGameModel().setCell("Void",pX,pY);
			}
		}
	}
}
