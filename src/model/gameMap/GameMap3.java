package model.gameMap;

import java.util.Random;

import model.Cell;

public class GameMap3 extends GameMap{
	
	public GameMap3() {
		super();
	}

	@Override
	protected void putBlocks(int[][] pBoard) {}

	@Override
	protected void putEnemie(int[][] pBoard) {
		Random r = new Random();
		int cont=0;
		for(int i=0;i<17 && cont<10;i++) {
			for(int j=0;j<11 && cont<10;j++) {
				 if(r.nextInt(100)<=20 && i+j>1) {
					 pBoard[i][j]=3;
					 cont++;
				 }	 
			}
		}
	}
}
