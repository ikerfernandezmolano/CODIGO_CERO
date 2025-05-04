package model.powerUp;

public class PowerUpFactory {
	private static PowerUpFactory myPUF=new PowerUpFactory();
	
	private PowerUpFactory() {}
	
	public static PowerUpFactory getPowerUpFactory() {
		return myPUF;
	}
	
	public PowerUp generate(String pType, int pX, int pY) {
		PowerUp pU = null;
		if(pType=="ExtraLife") pU=new ExtraLife(pX,pY);
		return pU;
	}
}
