package model.bomberman;

public class WhiteBomberman extends Bomberman{
	
	public WhiteBomberman(int pX, int pY) {
		super(pX,pY,10);
	}
	
	@Override
	public int getId() {
		return 41;
	}
}
