package model;

public class Bomberman {
	private boolean blanco;
	private int bombas;
	
	public Bomberman(boolean pBlanco) {
		this.blanco=pBlanco;
		if(pBlanco) 
			this.bombas=20;
		else this.bombas=1;
	}
}