package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;

public class GestorDePantalla extends Observable{
	
	/* Los números de listaPantalla representan:
	 * Hueco vacío = 0
	 * Bloque blando = 1
	 * Bloque duro = 2
	 * Enemigo = 3
	 * BomberMan = 4
	 */
	private int[] listaPantalla = new int[17*11];;
	private ArrayList<Integer> bloquesCambiados;
	private int modeloPantalla=1;
	private static GestorDePantalla miGP=new GestorDePantalla();
	
	private GestorDePantalla() {
		crearPantalla();
	}
	
	public static GestorDePantalla getGestorDePantalla() {
		return miGP;
	}
	
	private void crearPantalla() {
		bloquesCambiados=new ArrayList<Integer>();
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
				bloquesCambiados.add(j * 17 + i);
			}
		}
	}
	
	private void colocarEnemigos() {
		
	}

}