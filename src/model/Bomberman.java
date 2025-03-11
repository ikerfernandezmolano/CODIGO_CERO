package model;

public class Bomberman {
	private boolean blanco;
	private int bombas;
	private static Bomberman miBM=new Bomberman(true);
	
	private Bomberman(boolean pBlanco) {
		this.blanco=pBlanco;
		if(pBlanco) 
			this.bombas=20;
		else this.bombas=1;
	}
	
	public static Bomberman getBomberman() {
		return miBM;
	}
}