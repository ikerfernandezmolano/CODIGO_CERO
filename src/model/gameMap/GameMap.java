package model.gameMap;

import model.Cell;

public abstract class GameMap {
	
	protected GameMap() {}
	
	public void createBoard(int[][] pBoard) {
		this.putBlocks(pBoard);
		this.putBomberman(pBoard);
		this.putEnemie(pBoard);
	}
	
	protected abstract void putBlocks(int[][] pBoard);
	
	protected void putBomberman(int[][] pBoard) {
		pBoard[0][0]=4;
	}
	
	protected abstract void putEnemie(int[][] pBoard);

}
