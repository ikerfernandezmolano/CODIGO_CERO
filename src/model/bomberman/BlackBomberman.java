package model.bomberman;

public class BlackBomberman extends Bomberman{
	public BlackBomberman(int pX, int pY) {
		super(pX,pY,1);
	}
	
	@Override
	public int getId() {
		return 42;
	}
	@Override
	public boolean mata() {
		return false;
	}
}
