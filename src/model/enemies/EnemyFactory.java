package model.enemies;


public class EnemyFactory {
private static EnemyFactory myEF=new EnemyFactory();
	
	private EnemyFactory() {}
	
	public static EnemyFactory getEnemieFactory() {
		return myEF;
	}
	
	public Enemy generate(String pType, int pX, int pY) {
		Enemy enemie=null;
		if(pType=="Default") enemie=new DefaultEnemy(pX,pY);
		return enemie;
	}
}
