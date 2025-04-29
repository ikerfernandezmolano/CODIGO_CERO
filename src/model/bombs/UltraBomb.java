package model.bombs;

import model.TimerModelTool;

public class UltraBomb extends Bomb{
	
	public UltraBomb(int pX, int pY) {
		super(pX,pY);
		TimerModelTool.getTimerModelTool().tiempoExplotar(pX, pY, 17, 0);
	}
	
	@Override
	public int getId() {
		return 52;
	}
}
