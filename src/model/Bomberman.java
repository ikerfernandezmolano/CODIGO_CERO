package model;

public class Bomberman {
	private boolean blanco;
	private int bombas;
	private int coordX;
	private int coordY;
	
	
	public Bomberman(boolean pBlanco) {
		this.blanco=pBlanco;
		if(pBlanco) {
			this.bombas=20;
		}//.
		else {
			this.bombas=1;
		}	
	}

}