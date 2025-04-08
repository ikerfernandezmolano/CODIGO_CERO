package model.enemies;

public class DefaultEnemie extends Enemie{
	
	public DefaultEnemie(int pX, int pY) {
		super(pX,pY);
	}
	@Override
	public boolean mata() {
		return true;
	}

}
