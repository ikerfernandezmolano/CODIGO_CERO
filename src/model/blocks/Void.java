package model.blocks;

public class Void extends Block{

	public Void(int pX,int pY) {
		super(pX,pY);
	}
	
	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Void";
	}
}
