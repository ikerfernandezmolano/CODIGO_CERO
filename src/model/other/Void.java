package model.other;

import model.InterfaceCell;

public class Void implements InterfaceCell{

	public Void(int pX,int pY) {}
	
	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Void";
	}
}
