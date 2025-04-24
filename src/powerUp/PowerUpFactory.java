package powerUp;

public class PowerUpFactory {
	private static PowerUpFactory myPU;
	
	private PowerUpFactory() {}
	
	public static PowerUpFactory getPowerUpFactory() {
		if(myPU == null) {
			myPU = new PowerUpFactory();
		}
		return myPU;
	}
	
	public PowerUp generate(int pX, int pY) {
		PowerUp pU = null;
		pU = new PowerUp(pX,pY);
		return pU;
	}
}
