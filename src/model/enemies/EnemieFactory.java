package model.enemies;


public class EnemieFactory {
private static EnemieFactory myEF=new EnemieFactory();
	
	private EnemieFactory() {}
	
	public static EnemieFactory getEnemieFactory() {
		return myEF;
	}
	
	public Enemie generate(String pType, int pX, int pY) {
		Enemie enemie=null;
		if(pType=="Default") enemie=new DefaultEnemie(pX,pY);
		return enemie;
	}
}
