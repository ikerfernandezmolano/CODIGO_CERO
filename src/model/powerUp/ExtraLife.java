package model.powerUp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class ExtraLife extends PowerUp{

	public ExtraLife(int pX, int pY) {
		super();
		colocar();
	}
	
	private void colocar() {
		Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
					colocarPowerUp();
            }
        };
        timer.schedule(timerTask, 15000);
	}

	private void colocarPowerUp(){
		Random r=new Random();
		if(!GameModel.getGameModel().checkFlag(GameModel.getGameModel().posBoolean("BOMBERMAN_POWERUP"))) {
			int x, y;
			do {
				x = r.nextInt(16);
				y = r.nextInt(10);
			} while (!GameModel.getGameModel().is("Void", x, y));
			if (GameModel.getGameModel().checkFlag(GameModel.getGameModel().posBoolean("POWERUP_IN_MAP")))
				GameModel.getGameModel().setCell("Void", GameModel.getGameModel().getCoord("PowerUp", 'X'), GameModel.getGameModel().getCoord("PowerUp", 'Y'));
			else GameModel.getGameModel().changeFlagStatus(GameModel.getGameModel().posBoolean("POWERUP_IN_MAP"), true);
			GameModel.getGameModel().setCell("ExtraLife", x, y);
			GameModel.getGameModel().changePosition("PowerUp", x, y);
		}
	}
	
	@Override
	public boolean is(String pType) {
		return pType.equals("ExtraLife") || pType.equals("PowerUp");
	}
	
}
