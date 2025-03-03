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
		else if(pTipoCasilla==4) casilla=new Bomberman(true); //he puesto true solo para que funcione
		else if(pTipoCasilla==5) casilla=new Bomba();
		else if(pTipoCasilla==6) casilla=new Explosion();
		setChanged();
		notifyObservers(new int[] {pTipoCasilla});
	}
	
	public boolean puedeMoverse() {
		if((casilla instanceof BloqueBlando) ||
				(casilla instanceof BloqueDuro)) {
			return false;
		}
		return true;
	}
	
}
