package model.blocks;

import model.InterfaceCell;

public abstract class Block implements InterfaceCell{
	
	protected Block(int pX, int pY) {}
	
	@Override
	public boolean canMove() {
		return false;
	}
}
