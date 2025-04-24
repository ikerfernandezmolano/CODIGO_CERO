package powerUp;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;
import model.interfaceCell.InterfaceCell;

public class PowerUp implements InterfaceCell{
	private Timer timer;
	
	public PowerUp(int pX, int pY) {
		
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="PowerUp";
	}

	@Override
	public int getId() {
		return 8;
	}

	@Override
	public boolean kills() {
		return false;
	}
	
	public void colocarPowerUp() {
		timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().colocarPowerUp();
            }
        };
        timer.schedule(timerTask, 10000);
	}
}
