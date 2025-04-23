package model.bombs;

import model.TimerModelTool;

public class BossyBomb extends Bomb{

	protected BossyBomb(int pX, int pY) {
		super(pX, pY);
		TimerModelTool.getTimerModelTool().tiempoExplotar(pX, pY, 3,1);
	}
	
	 @Override
	 public int getId() {
		 return 53;
	 }
}
