package model;

import java.util.Observable;
import java.util.Random;

public class Tablero extends Observable{
	
	/* Los números de listaPantalla representan:
	 * Hueco vacío = 0
	 * Bloque blando = 1
	 * Bloque duro = 2
	 * Enemigo = 3
	 * Bomberman = 4
	 * Bomba = 5
	 * Explosion = 6
	 */
	private Casilla[][] tablero = new Casilla[17][11];
	private int[] listaPantalla=new int[17*11];
	private static Tablero miTAB=new Tablero();
	
	private Tablero() {}
	
	public static Tablero getTablero() {
		return miTAB;
	}
	
	public void crearPantalla() {
		colocarBloques();
		colocarEnemigos();
		colocarBomberman();
		imprimir();
		setChanged();
		notifyObservers(new Object[] {listaPantalla});
	}
	
	private void colocarBloques() {
		Random r = new Random();
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				if(i%2!=0 && j%2!=0) {
					tablero[i][j] = new Casilla(i, j, 2);
					listaPantalla[j*17+i]=2;
				} else {
					if(r.nextInt(100)<=65 && i+j>1) {
						tablero[i][j] = new Casilla(i, j, 1);
						listaPantalla[j*17+i]=1;
					} else {
						tablero[i][j] = new Casilla(i, j, 0);
						listaPantalla[j*17+i]=0;
					}
				}
			}
		}
	}
	
	private void colocarEnemigos() {
		
	}
	
	private void imprimir() {
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				System.out.println("Casilla en coords: " + i + ", " + j + " - Tipo de objeto: " + tablero[i][j].printTipo() );
			}
		}
	}
	
	private void colocarBomberman() {
		tablero[0][0].setCasilla(4);
		listaPantalla[0]=4;
		colocarPrueba();
	}
	
	private void colocarPrueba() {
		tablero[1][0].setCasilla(3);
		listaPantalla[1]=3;
		tablero[2][0].setCasilla(5);
		listaPantalla[2]=5;
		tablero[3][0].setCasilla(6);
		listaPantalla[3]=6;
	}
	
	public boolean puedeMoverse(int pX, int pY) {
		boolean puedeMoverse = true;
		if(pX<0 && pX>=17 && pY<0 && pX>=11) {
			puedeMoverse=false;
		} else if(tablero[pX][pY].mismoTipoCasilla(1) || 
				tablero[pX][pY].mismoTipoCasilla(2)) {
			puedeMoverse=false;
		}
		return puedeMoverse;
	}

}