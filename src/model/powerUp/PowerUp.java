package model.powerUp;

import model.interfaceCell.InterfaceCell;

public abstract class PowerUp implements InterfaceCell{
	private int x;
	private int y;
	
	protected PowerUp(int pX, int pY) {
		x=pX;
		y=pY;
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public int getId() {
		return 8;
	}

	@Override
	public boolean kills() {
		return false;
	}
}
