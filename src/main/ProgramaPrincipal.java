package main;
import model.GestorDePantalla;
import viewController.Pantalla;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		//MODELO//
		GestorDePantalla.getGestorDePantalla();
		//VISTA//
		@SuppressWarnings("unused")
		Pantalla pantalla= new Pantalla();
	}
}
