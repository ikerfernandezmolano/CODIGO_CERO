package model.gameMap;

import java.util.Random;

import model.Cell;

public class GameMap1 implements GameMap{
	
	public GameMap1() {}
	
	public void createBoard(int[][] pBoard) {
		this.putBlocks(pBoard);
		this.putBomberman(pBoard);
		this.putEnemie(pBoard);
	}

	@Override
	public void putBlocks(int[][] pBoard) {
		Random r = new Random();
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				if(i%2!=0 && j%2!=0) 
					pBoard[i][j]=2;
				else if(r.nextInt(100)<=65 && i+j>1) 
					pBoard[i][j]=1;
			}
		}
	}

	@Override
	public void putEnemie(int[][] pBoard) {
		Random r = new Random();
		int cont=0;
		for(int i=0;i<17 && cont<10;i++) {
			for(int j=0;j<11 && cont<10;j++) {
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
