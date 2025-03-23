package model.bomberman;

import model.interfaceCell.InterfaceCell;

public abstract class Bomberman implements InterfaceCell{
	private int bombas;
	
	protected Bomberman(int pX, int pY, int pBombas) {
		this.bombas=pBombas;
	}
	
	@Override
	public boolean is(String pType) {
		return pType=="Bomberman";
	}
	
	@Override
	public boolean canMove() {
		return false;
	}
	
	@Override
	public int getId() {
		return 4;
	}
}