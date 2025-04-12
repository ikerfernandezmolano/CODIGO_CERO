package model.enemies;
import model.interfaceCell.InterfaceCell;

public abstract class Enemy implements InterfaceCell{
	
	protected Enemy(int pX,int pY) {}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Enemy";
	}
	
	@Override
	public int getId() {
		return 3;
	}
	
	@Override
	public boolean kills() {
		return true;
	}
}
