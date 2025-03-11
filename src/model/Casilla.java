package model;

import java.util.Observable;

public class Casilla extends Observable{
	private Object casilla;
	private int x,y;
	
	public Casilla(int pX, int pY) {
		this.x=pX;
		this.y=pY;
	}
	
	public void setCasilla(int pTipoCasilla) {
		if(pTipoCasilla==0) casilla=null;
		else if(pTipoCasilla==1) casilla=new BloqueBlando();
		else if(pTipoCasilla==2) casilla=new BloqueDuro();
		else if(pTipoCasilla==3) casilla=new Enemigo();
		else if(pTipoCasilla==4) casilla=Bomberman.getBomberman();
		else if(pTipoCasilla==5) casilla=new Bomba(x,y);
		else if(pTipoCasilla==6) casilla=new Explosion(x,y);
		setChanged();
		notifyObservers(new int[] {pTipoCasilla});
	}

	public boolean puedeMoverse() {
		if((casilla instanceof BloqueBlando) ||
				(casilla instanceof BloqueDuro) ||
				(casilla instanceof Bomba)) 
			return false;
		return true;
	}
	
	public boolean esDuro() {
		return casilla instanceof BloqueDuro;
	}
	
	public boolean esBomba() {
		return casilla instanceof Bomba;
	}
	
	public boolean esBomberman() {
		return casilla instanceof Bomberman;
	}
	
	public boolean esExplosion() {
		return casilla instanceof Explosion;
	}
	
	public void setMuerto() {
		if(casilla instanceof Bomberman) {
			setChanged();
			notifyObservers(new int[] {7});
		}
	}
}