package model.bombs;

import model.TimerModelTool;

public class SuperBomb extends Bomb{
	
	public SuperBomb(int pX, int pY) {
		super(pX,pY);
		TimerModelTool.getTimerModelTool().tiempoExplotar(pX, pY, 1,0);
	}
	
	@Override
	public int getId() {
		return 51;
	}
}
