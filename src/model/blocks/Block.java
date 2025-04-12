package model.blocks;

import model.interfaceCell.InterfaceCell;

public abstract class Block implements InterfaceCell{
	
	protected Block(int pX, int pY) {}
	
	@Override
	public boolean kills() {
		return false;
	}
}
