package model.gameMap;

import java.util.Random;

public class GameMap3 implements GameMap{
	private static int WIDTH,HEIGHT;
	
	public GameMap3(int pWidth,int pHeight) {
		WIDTH=pWidth;
		HEIGHT=pHeight;
	}
	
	public void createBoard(int[][] pBoard) {
		this.putBlocks(pBoard);
		this.putBomberman(pBoard);
		this.putEnemie(pBoard);
	}

	@Override
	public void putBlocks(int[][] pBoard) {}

	@Override
	public void putEnemie(int[][] pBoard) {
		Random r = new Random();
		int cont=0;
		for(int i=0;i<WIDTH && cont<15;i++) {
			for(int j=0;j<HEIGHT && cont<15;j++) {
				 if(r.nextInt(100)<=4 && i+j>1) {
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
