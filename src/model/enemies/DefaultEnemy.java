package model.enemies;

public class DefaultEnemy extends Enemy{
	
	public DefaultEnemy(int pX, int pY) {
		super(pX,pY);
	}
	@Override
	public boolean mata() {
		return true;
	}

}
