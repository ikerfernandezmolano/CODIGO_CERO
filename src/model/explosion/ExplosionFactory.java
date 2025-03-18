package model.explosion;

public class ExplosionFactory {
	private static ExplosionFactory myEF=new ExplosionFactory();
	
	private ExplosionFactory() {}
	
	public static ExplosionFactory getExplosionFactory() {
		return myEF;
	}
	
	public Explosion generate(String pType, int pX, int pY) {
		Explosion explosion=null;
		if(pType=="Default") explosion=new DefaultExplosion(pX,pY);
		return explosion;
	}
}
