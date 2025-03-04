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
		}
		else {
			this.bombas=1;
		}	
	}
	
	public boolean moverse(String pTecla) {
		int x=coordX;
		int y=coordY;
		boolean movido=false;
		
		if(pTecla=="w") {
			y--;
		}
		else if (pTecla=="s") {
			y++;
		}
		else if (pTecla=="a") {
			x--;
		}
		else if(pTecla=="d") {
			x++;
		}
		if(Tablero.getTablero().puedeMoverse(x, y)) {
			Tablero.getTablero().moverBomberman(coordX,coordY,x,y);
			coordX=x;
			coordY=y;
			movido=true;
		}
		return movido;
	}

}

