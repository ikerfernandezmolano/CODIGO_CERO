package model;

public class Casilla {
	private Object casilla;
	private int x,y;
	
	public Casilla(int pX, int pY,int pTipoCasilla) {
		this.x=pX;
		this.y=pY;
		setCasilla(pTipoCasilla);
	}
	
	public void setCasilla(int pTipoCasilla) {
		if(pTipoCasilla==0) casilla=null;
		else if(pTipoCasilla==1) casilla=new Bloque(false);
		else if(pTipoCasilla==2) casilla=new Bloque(true);
		else if(pTipoCasilla==3) casilla=new Enemigo();
		else if(pTipoCasilla==4) casilla=new Bomberman(true); //he puesto true solo para que funcione
		else if(pTipoCasilla==5) casilla=new Bomba();
	}
	
	public boolean mismoTipoCasilla(int pTipo) {
		boolean mismoTipo=false;
		if((casilla==null && pTipo==0)||
				(casilla instanceof Enemigo && pTipo==3)||
				(casilla instanceof Bomberman && pTipo==4)||
				(casilla instanceof Bomba && pTipo==5)) {
			mismoTipo=true;
		} else if (casilla instanceof Bloque) {
			boolean duro= ((Bloque) casilla).esDuro();
			if((!duro && pTipo==1)||
				(duro && pTipo==2)) {
				mismoTipo=true;
			}
		}
		return mismoTipo;
	}
	
	public String printTipo() {
		if(this.casilla==null) return "Vacío";
		String nom=casilla.getClass().getSimpleName();
		return nom;
	}
	
}
