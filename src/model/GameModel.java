package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.gameMap.GameMap;
import model.gameMap.GameMapFactory;

public class GameModel{
	
	private Cell[][] board;
	private static GameModel myGM=new GameModel();
	private boolean partidaTerminada;
	private int xBM,yBM;
	private String bomberman;
	private GameMap map;
	
	private GameModel() {
		initialize();
	}
	
	public static GameModel getGameModel() {
		return myGM;
	}
	
	public void configurarJuego(String pBomberman, int pMap) {
		bomberman=pBomberman;
		map=GameMapFactory.getGameMapFactory().generate(pMap);
	}
	
	private void initialize() {
		board = new Cell[17][11];
		for(int i=0;i<17;i++) {
			for(int j=0;j<11;j++) {
				board[i][j]=new Cell(i, j);
			}
		}
	}
	
//--------------------TABLERO_CREATE-------------------------------	
	
	public void crearTablero() {
		int[][] tab=new int[17][11];
		map.createBoard(tab);
		for(int i=0;i<tab.length;i++) {
			for(int j=0;j<tab[i].length;j++) {
				int type=tab[i][j];
				if(type==1) board[i][j].setCell("Soft");
				else if(type==2) board[i][j].setCell("Hard");
				else if(type==3) board[i][j].setCell("Enemie");
				else if(type==4) board[i][j].setCell(bomberman);					
			}
		}
	}

//------------------------MOVEMENT--------------------------	
	
	public boolean moverseBM(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			xBM=pXn;
			yBM=pYn;
			moverseConBomba(pXact,pYact);
			if(board[pXn][pYn].is("Explosion"))
				partidaTerminada=true;
			else board[pXn][pYn].setCell(bomberman);
		}
		return puede;
	}
	
	private boolean puedeMoverse(int pX, int pY) {
		if(pX<0 || pX>=17 || pY<0 || pY>=11) return false;
		return board[pX][pY].canMove();
	}
	
	private void moverseConBomba(int pXact,int pYact) {
		if(board[pXact][pYact].is("Bomb")) 
			board[pXact][pYact].setCell("Super");
		else board[pXact][pYact].setCell("Void");
	}
	
//-----------------------ENEMIES--------------------------------

	public synchronized void moverEnemigos() {
	    List<int[]> enemigos = new ArrayList<>();
	    // Guardar las posiciones originales de los enemigos
	    for (int i = 0; i < 17; i++) {
	        for (int j = 0; j < 11; j++) {
	            if (board[i][j].is("Enemie")) {
	                enemigos.add(new int[]{i, j});
	            }
	        }
	    }
	    // Mover los enemigos de la lista original
	    for (int[] pos : enemigos) {
	        int x = pos[0], y = pos[1];
	        // Verificar si sigue siendo un enemigo antes de moverlo
	        if (board[x][y].is("Enemie")) {
	            moverEnemigo(x, y);
	        }
	    }
	}
	
	private synchronized void moverEnemigo(int pX, int pY) {
		Random r = new Random();
		boolean moved = false;
	
		for(int i=0; i<15 && !moved; i++) {
			int direccion = r.nextInt(4);
		    int newX = pX; 
		    int newY = pY;
	
		    if (direccion == 0) newY = pY - 1; // Arriba
		    else if (direccion == 1) newY = pY + 1; // Abajo
		    else if (direccion == 2) newX = pX - 1; // Izquierda
		    else if (direccion == 3) newX = pX + 1; // Derecha
	
		    if (puedeMoverse(newX, newY)&& board[newX][newY].is("Void")) {
		    	synchronized (board){
		    		if (board[pX][pY].is("Enemie")) {
	                    board[pX][pY].setCell("Void");
	                    board[newX][newY].setCell("Enemie");
	                    moved = true;
	                }
		    	}
		    		
		    }
		 }
	}
	
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY) {
		if(!detectarBomberman(pX,pY))
			board[pX][pY].setCell("Explosion");
		for(int i=-1; i<2; i=i+2) {
			if(pX+i >= 0 && pX+i<17 && !board[pX+i][pY].is("Hard") &&
					!detectarBomberman(pX+i,pY))
			    board[pX+i][pY].setCell("Explosion");
			if(pY+i >=0 && pY+i<11 && !board[pX][pY+i].is("Hard") &&
					!detectarBomberman(pX,pY+i)) 
			    board[pX][pY+i].setCell("Explosion");
		}
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		boolean isBM=false;
		if(xBM==pX && yBM==pY) {
			isBM=true;
			partidaTerminada=true;
			board[pX][pY].setMuerto();
		}
		return isBM;
	}
	
	public void quitarExplosion(int pX, int pY) {
		board[pX][pY].setCell("Void");
	}
	
	public void colocarBomba(int pX, int pY) {
		board[pX][pY].setCell("Super");
	}
	
	public boolean hasBomb() {
		return board[xBM][yBM].hasBomb();
	}
	
//------------------------FIN_PARTIDA----------------------------
	
	public boolean getEstadoPartida() {
		return partidaTerminada;
	}
	
//------------------------CELLS-------------------------------
	
	public Cell getCell(int pX,int pY) {
		return board[pX][pY];
	}
}