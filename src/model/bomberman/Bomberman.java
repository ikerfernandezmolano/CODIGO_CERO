package model.bomberman;

import model.interfaceCell.InterfaceCell;

public abstract class Bomberman implements InterfaceCell{
	private int bombs;
	
	protected Bomberman(int pX, int pY, int pBombas) {
		this.bombs=pBombas;
	}
	
	@Override
	public boolean is(String pType) {
		return pType=="Bomberman";
	}
	
	@Override
	public boolean canMove() {
		return true;
	}
	
	public int getBombs() {
		return bombs;
	}
	
	public String getTypeBombs() {
		return "Super";
	}
	
	@Override
	public boolean kills() {
		return true;
	}
}