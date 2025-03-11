package model;

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
	private boolean partidaTerminada;
	
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
	
	public boolean moverse(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			moverseConBomba(pXact,pYact);
			if(tablero[pXn][pYn].esExplosion())
				partidaTerminada=true;
			else tablero[pXn][pYn].setCasilla(4);	
		}
		return puede;
	}
	
	private void moverseConBomba(int pXact,int pYact) {
		if(tablero[pXact][pYact].esBomba())
			tablero[pXact][pYact].setCasilla(5);
		else tablero[pXact][pYact].setCasilla(0);
	}
	
	private boolean puedeMoverse(int pX, int pY) {
		if(pX<0 || pX>=17 || pY<0 || pY>=11) return false;
		return tablero[pX][pY].puedeMoverse();
	}
	
//------------------------BOMBAS-------------------------------	
	
	public void explotar(int pX, int pY) {
		tablero[pX][pY].setCasilla(6);
		for(int i=-1; i<2; i=i+2) {
			if(pX+i >= 0 && pX+i<17 && !tablero[pX+i][pY].esDuro() &&
					!detectarBomberman(pX+i,pY))
			    tablero[pX+i][pY].setCasilla(6);
			if(pY+i >=0 && pY+i<11 && !tablero[pX][pY+i].esDuro() &&
					!detectarBomberman(pX,pY+i)) 
			    tablero[pX][pY+i].setCasilla(6);
		}
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		boolean esBM=tablero[pX][pY].esBomberman();
		if(esBM) {
			partidaTerminada=true;
			tablero[pX][pY].setMuerto();
		}
		return esBM;
	}
	
	public void quitarExplosion(int pX, int pY) {
		tablero[pX][pY].setCasilla(0);
	}
	
	public void colocarBomba(int pX, int pY) {
		tablero[pX][pY].setCasilla(5);
	}
	
//------------------------FIN_PARTIDA----------------------------
	
	public boolean getEstadoPartida() {
		return partidaTerminada;
	}
	
//------------------------CASILLAS-------------------------------
	
	public Casilla getCasilla(int pX,int pY) {
		return tablero[pX][pY];
	}

}