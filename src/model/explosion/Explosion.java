package model.explosion;

import model.interfaceCell.InterfaceCell;

public abstract class Explosion implements InterfaceCell{
	
	protected Explosion (int pX, int pY) {}
	
	protected abstract void tiempo(int pX, int pY);

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Explosion";
	}
	
	@Override
	public int getId() {
		return 6;
	}
}

