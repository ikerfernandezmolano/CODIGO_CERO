package model.powerUp;

public class PowerUpFactory {
	private static PowerUpFactory myPU;
	
	private PowerUpFactory() {}
	
	public static PowerUpFactory getPowerUpFactory() {
		if(myPU == null) {
			myPU = new PowerUpFactory();
		}
		return myPU;
	}
	
	public PowerUp generate(String pType, int pX, int pY) {
		PowerUp pU = null;
		if(pType=="ExtraLife") pU=new ExtraLife(pX,pY);
		return pU;
	}
}
