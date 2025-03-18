package model;

import java.util.Random;

public class Tablero{
	
	private Cell[][] tablero;
	private static Tablero miTAB=new Tablero();
	private boolean partidaTerminada;
	private int xBM,yBM;
	
	private Tablero() {
		initialize();
	}
	
	public static Tablero getTablero() {
		return miTAB;
	}
	
	private void initialize() {
		tablero = new Cell[17][11];
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				tablero[i][j]=new Cell(i, j);
			}
		}
	}
	
//--------------------TABLERO_CREATE-------------------------------	
	
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
					tablero[i][j].setCell("Hard");
				else if(r.nextInt(100)<=65 && i+j>1) 
					tablero[i][j].setCell("Soft");
			}
		}
	}
	
	private void colocarEnemigos() {
		
	}
	
	private void colocarBomberman() {
		tablero[0][0].setCell("White");
	}
	
//------------------------MOVEMENT--------------------------	
	
	public boolean moverse(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			xBM=pXn;
			yBM=pYn;
			moverseConBomba(pXact,pYact);
			if(tablero[pXn][pYn].is("Explosion"))
				partidaTerminada=true;
			else tablero[pXn][pYn].setCell("White");
		}
		return puede;
	}
	
	private void moverseConBomba(int pXact,int pYact) {
		if(tablero[pXact][pYact].is("Bomb")) 
			tablero[pXact][pYact].setCell("Super");
		else tablero[pXact][pYact].setCell("Void");
	}
	
	private boolean puedeMoverse(int pX, int pY) {
		if(pX<0 || pX>=17 || pY<0 || pY>=11) return false;
		return tablero[pX][pY].canMove();
	}
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY) {
		if(!detectarBomberman(pX,pY))
			tablero[pX][pY].setCell("Explosion");
		for(int i=-1; i<2; i=i+2) {
			if(pX+i >= 0 && pX+i<17 && !tablero[pX+i][pY].is("Hard") &&
					!detectarBomberman(pX+i,pY))
			    tablero[pX+i][pY].setCell("Explosion");
			if(pY+i >=0 && pY+i<11 && !tablero[pX][pY+i].is("Hard") &&
					!detectarBomberman(pX,pY+i)) 
			    tablero[pX][pY+i].setCell("Explosion");
		}
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		boolean isBM=false;
		if(xBM==pX && yBM==pY) {
			isBM=true;
			partidaTerminada=true;
			tablero[pX][pY].setMuerto();
		}
		return isBM;
	}
	
	public void quitarExplosion(int pX, int pY) {
		tablero[pX][pY].setCell("Void");
	}
	
	public void colocarBomba(int pX, int pY) {
		tablero[pX][pY].setCell("Super");
	}
	
//------------------------FIN_PARTIDA----------------------------
	
	public boolean getEstadoPartida() {
		return partidaTerminada;
	}
	
//------------------------CELLS-------------------------------
	
	public Cell getCell(int pX,int pY) {
		return tablero[pX][pY];
	}
}