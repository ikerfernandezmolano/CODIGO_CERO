package model.enemies;
import model.InterfaceCell;

public abstract class Enemie implements InterfaceCell{
	
	protected Enemie(int pX,int pY) {}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Enemie";
	}
}
