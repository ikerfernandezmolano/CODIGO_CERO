package model;

import java.util.Observer;
import java.util.Random;

public class Tablero{
	
	 /*Hueco vacío = 0
	 * Bloque blando = 1
	 * Bloque duro = 2
	 * Enemigo = 3
	 * Bomberman = 4
	 * Bomba = 5
	 * Explosion = 6
	 */
	private Casilla[][] tablero;
	private static Tablero miTAB=new Tablero();
	
	private Tablero() {
		initialize();
	}
	
	public static Tablero getTablero() {
		return miTAB;
	}
	
	private void initialize() {
		tablero = new Casilla[17][11];
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				tablero[i][j]=new Casilla(i,j);
			}
		}
	}
	
//--------------------CREAR_TABLERO-------------------------------	
	
	public void crearTablero() {
		colocarBloques();
		colocarEnemigos();
		colocarBomberman();
	}
	
	private void colocarBloques() {
		Random r = new Random();
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				if(i%2!=0 && j%2!=0) 
					tablero[i][j].setCasilla(2);
				else if(r.nextInt(100)<=65 && i+j>1) 
					tablero[i][j].setCasilla(1);
			}
		}
	}
	
	private void colocarEnemigos() {
		
	}
	
	private void colocarBomberman() {
		tablero[0][0].setCasilla(4);
	}
	
//------------------------MOVIMIENTO---------------------------	
	
	private boolean puedeMoverse(int pX, int pY) {
		if(pX<0 || pX>=17 || pY<0 || pY>=11) return false;
		return tablero[pX][pY].puedeMoverse();
	}
	
	public boolean moverse(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			tablero[pXact][pYact].setCasilla(0);
			tablero[pXn][pYn].setCasilla(4);
		}
		return puede;
	}
	
//------------------------BOMBAS-------------------------------	
	
	public void explotar(int x, int y) {
		tablero[x][y].setCasilla(6);
		for(int i=-1; i<2; i=i+2) {
			if(x+i >= 0 && x+i<17 && !tablero[x+i][y].esDuro()) {
				tablero[x+i][y].setCasilla(6);
			}
			if(y+i >=0 && y+i<11 && !tablero[x][y+i].esDuro()) {
				tablero[x][y+i].setCasilla(6);
			}
		}
	}
	
	public void quitarExplosion(int pX, int pY) {
		tablero[pX][pY].setCasilla(0);
	}
	
	public void colocarBomba(int pX, int pY) {
		tablero[pX][pY].setCasilla(5);
	}
	
//------------------------OBSERVERS-------------------------------
	
	public void addObserver(Observer pCV,int pX,int pY) {
		tablero[pX][pY].addObserver(pCV);
	}

}