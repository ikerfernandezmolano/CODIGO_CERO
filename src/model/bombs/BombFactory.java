package model.bombs;

public class BombFactory {
	private static BombFactory myBF=new BombFactory();
	
	private BombFactory() {}
	
	public static BombFactory getBombFactory() {
		return myBF;
	}
	
	public Bomb generate(String pType, int pX, int pY) {
		Bomb bomb=null;
		if(pType=="Super") bomb=new SuperBomb(pX,pY);
		else if(pType=="Ultra") bomb=new UltraBomb(pX,pY);
		else if(pType=="Bossy") bomb=new BossyBomb(pX,pY); 
		return bomb;
	}
}
