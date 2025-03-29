package main;
import model.GameModel;
import viewController.GameView;

public class ProgramaPrincipal {
	public static void main(String[] args) {
		//MODEL//
		GameModel.getGameModel();
		//VISTA//
		@SuppressWarnings("unused")
		GameView gameView= new GameView();
	}
}