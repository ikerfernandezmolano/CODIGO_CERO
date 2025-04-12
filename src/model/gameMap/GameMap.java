package model.gameMap;

public interface GameMap {
	
	public void createBoard(int[][] pBoard);
	public void putBlocks(int[][] pBoard);
	public void putBomberman(int[][] pBoard);
	public void putEnemie(int[][] pBoard);

}
