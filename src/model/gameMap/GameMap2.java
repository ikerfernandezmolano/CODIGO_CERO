package model.gameMap;

import java.util.Random;

import model.Cell;

public class GameMap2 extends GameMap{

	public GameMap2() {
		super();
	}

	@Override
	protected void putBlocks(int[][] pBoard) {
		Random r = new Random();
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				 if(r.nextInt(100)<=65 && i+j>1) 
					pBoard[i][j]=1;
			}
		}	
	}

	@Override
	protected void putEnemie(int[][] pBoard) {
		Random r = new Random();
		int cont=0;
		for(int i=0;i<17 && cont<10;i++) {
			for(int j=0;j<11 && cont<10;j++) {
				 if(r.nextInt(100)<=4 && i+j>1) {
					 pBoard[i][j]=3;
					 cont++;
				 }	 
			}
		}
	}
}
