package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;

public class Tablero extends Observable{
	
	/* Los números de listaPantalla representan:
	 * Hueco vacío = 0
	 * Bloque blando = 1
	 * Bloque duro = 2
	 * Enemigo = 3
	 * BomberMan = 4
	 */
	private int[] listaPantalla = new int[17*11];
	private int[] bloquesCambiados;
	private int modeloPantalla=1;
	private static Tablero miTAB=new Tablero();
	
	private Tablero() {}
	
	public static Tablero getTablero() {
		return miTAB;
	}
	
	public void crearPantalla() {
		bloquesCambiados= new int[17*11];
		colocarBloques();
		colocarEnemigos();
		setChanged();
		notifyObservers(new Object[] {listaPantalla,bloquesCambiados,modeloPantalla});
	}
	
	private void colocarBloques() {
		Random r = new Random();
		for(int j=0;j<11;j++) {
			for(int i=0;i<17;i++) {
				if(i%2!=0 && j%2!=0) {
					listaPantalla[j * 17 + i] = 2;
				} else {
					if(r.nextInt(100)<=65 && i+j>1) {
						listaPantalla[j * 17 + i] = 1;
					} else {
						listaPantalla[j * 17 + i] = 0;
					}
				}
				bloquesCambiados[j * 17 + i] = j * 17 + i;
			}
		}
	}
	
	private void colocarEnemigos() {
		
	}
	
	public boolean puedeMoverse(int pX, int pY) {
		boolean puedeMoverse = true;
		if(pX<0 && pX>=17 && pY<0 && pX>=11) {
			puedeMoverse=false;
		} else if(listaPantalla[pY*17+pX]==1 || listaPantalla[pY*17+pX]==2) {
			puedeMoverse=false;
		}
		return puedeMoverse;
	}

}