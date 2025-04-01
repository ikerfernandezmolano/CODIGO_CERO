package model.bomberman;

public class BombermanFactory {
	private static BombermanFactory myBF=new BombermanFactory();
	
	private BombermanFactory() {}
	
	public static BombermanFactory getBombermanFactory() {
		return myBF;
	}
	
	public Bomberman generate(String pType, int pX, int pY) {
		Bomberman bomberman=null;
		if(pType=="White") bomberman=new WhiteBomberman(pX,pY);
		else bomberman=new BlackBomberman(pX,pY);
		return bomberman;
	}
	
}
