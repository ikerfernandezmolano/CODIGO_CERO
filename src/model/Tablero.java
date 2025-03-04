package model;

import java.util.Observer;
import java.util.Random;

public class Tablero{
	
	 /* Hueco vacío = 0
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
	
	public boolean puedeMoverse(int pX, int pY) {
		if(pX<0 && pX>=17 && pY<0 && pX>=11) return false;
		return tablero[pX][pY].puedeMoverse();
	}
	
	public void addObserver(Observer pCV,int pX,int pY) {
		tablero[pX][pY].addObserver(pCV);
	}

}