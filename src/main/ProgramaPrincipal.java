package main;
import model.Tablero;
import viewController.Pantalla;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		//MODEL//
		Tablero.getTablero();
		//VISTA//
		@SuppressWarnings("unused")
		Pantalla pantalla= new Pantalla();
	}
}