package model.powerUp;

import model.interfaceCell.InterfaceCell;

public abstract class PowerUp implements InterfaceCell{
	
	protected PowerUp() {}

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
