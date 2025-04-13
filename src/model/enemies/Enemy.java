package model.enemies;
import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;
import model.interfaceCell.InterfaceCell;

public abstract class Enemy implements InterfaceCell{
	Timer timer=null;
	
	protected Enemy(int pX,int pY) {
		moverse(pX,pY);
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Enemy";
	}
	
	@Override
	public int getId() {
		return 3;
	}
	
	@Override
	public boolean kills() {
		return true;
	}
	
	private void moverse(int pX, int pY) {
		timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().moverseEnemigo(pX, pY);
            }
        };
        timer.schedule(timerTask, 1000);
	}
	
	public void stopTimer() {
		if (timer != null) {
            timer.cancel();
            timer.purge();
        }
		timer=null;
	}
}