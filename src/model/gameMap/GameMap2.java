package model.gameMap;

import java.util.Random;

public class GameMap2 implements GameMap{
	private static int WIDTH,HEIGHT;
	
	public GameMap2(int pWidth,int pHeight) {
		WIDTH=pWidth;
		HEIGHT=pHeight;
	}
	
	public void createBoard(int[][] pBoard) {
		this.putBlocks(pBoard);
		this.putBomberman(pBoard);
		this.putEnemie(pBoard);
	}

	@Override
	public void putBlocks(int[][] pBoard) {
		Random r = new Random();
		for(int i=0;i<WIDTH;i++) {
			for(int j=0;j<HEIGHT;j++) {
				 if(r.nextInt(100)<=65 && i+j>1) 
					pBoard[i][j]=1;
			}
		}	
	}

	@Override
	public void putEnemie(int[][] pBoard) {
		Random r = new Random();
		int cont=0;
		for(int i=0;i<WIDTH && cont<10;i++) {
			for(int j=0;j<HEIGHT && cont<10;j++) {
				 if(r.nextInt(100)<=15 && i+j>1 && pBoard[i][j]==0) {
					 pBoard[i][j]=3;
					 cont++;
				 }	 
			}
		}
	}
	
	@Override
	public void putBomberman(int[][] pBoard) {
		pBoard[0][0]=4;
	}
}
