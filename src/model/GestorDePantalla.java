package model;

import java.util.Observable;
import java.util.Random;

import javax.swing.JLabel;

import viewController.Bloque;

public class GestorDePantalla extends Observable{
	
	/* Los números de listaPantalla representan:
	 * Hueco vacío = 0
	 * Bloque blando = 1
	 * Bloque duro = 2
	 * Enemigo = 3
	 * BomberMan = 4
	 */
	private int[] listaPantalla;
	private int modeloPantalla;
	private static GestorDePantalla miGP;
	
	private GestorDePantalla() {
		crearPantalla();
	}
	
	public static GestorDePantalla getGestorDePantalla() {
		return miGP;
	}
	
	private void crearPantalla() {
		listaPantalla = new int[17*11];
		colocarBloques();
		colocarEnemigos();
		setChanged();
		notifyObservers(new Object[] {listaPantalla,modeloPantalla});
	}
	
	private void colocarBloques() {
		Random r = new Random();
		for(int j=0;j<11;j++) {
			for(int i=0;i<17;i++) {
				if(i%2!=0 && j%2!=0) {
					listaPantalla[i+j] = 2;
				} else {
					if(r.nextInt(100)<=65 && i+j>1) {
						listaPantalla[i+j] = 1;
					} else {
						listaPantalla[i+j] = 0;
					}
				}
			}
		}
	}
	
	private void colocarEnemigos() {
		
	}

}