package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.gameMap.GameMap;
import model.gameMap.GameMapFactory;
import viewController.TimerViewTool;

public class GameModel{
	private static GameModel myGM=new GameModel();
	private Cell[][] board;
	private boolean partidaTerminada;
	
	
	private int xBM, yBM;
	private String bomberman;
	private int bombasBM;
	private String typeBomb;
	
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
				else if(type==3) board[i][j].setCell("Enemy");
				else if(type==4) {
					board[i][j].setCell(bomberman);
					bombasBM=board[i][j].getBombs();
					typeBomb=board[i][j].typeBombs();
				}
			}
		}
		TimerModelTool.getTimerModelTool().startEnemyMovement(1000);
	}

//------------------------MOVEMENT--------------------------	
	
	public boolean moverseBM(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			xBM=pXn;
			yBM=pYn;
			moverseConBomba(pXact,pYact);
			if(board[pXn][pYn].mata()) {
				partidaTerminada=true;
			    board[pXn][pYn].setMuerto(bomberman);
			}
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
			board[pXact][pYact].setCell(typeBomb);
		else board[pXact][pYact].setCell("Void");
	}
	
//-----------------------ENEMIES--------------------------------

	public synchronized void moverEnemigos() {
	    List<int[]> enemigos = new ArrayList<>();
	    // Guardar las posiciones originales de los enemigos
	    for (int i = 0; i < 17; i++) {
	        for (int j = 0; j < 11; j++) {
	            if (board[i][j].is("Enemy")) 
	                enemigos.add(new int[]{i, j});
	        }
	    }
	    // Mover los enemigos de la lista original
	    for (int[] pos : enemigos) {
	        int x = pos[0], y = pos[1];
	        // Verificar si sigue siendo un enemigo antes de moverlo
	        if (board[x][y].is("Enemy"))
	            moverEnemigo(x, y);
	    }
	}
	
	private synchronized void moverEnemigo(int pX, int pY) {
		Random r = new Random();
		boolean moved = false;
	
		for(int i=0; i<15 && !moved; i++) {
			int n = r.nextInt(4);
		    int x=pX,y=pY;
		    
			if(n<2) x = (n==0) ? x+1:x-1;
			else y = (n==2) ? y+1:y-1;
	
		    if (puedeMoverse(x, y)) {
		    	synchronized (board){
		    		if(board[x][y].is("Bomberman")) {
		    			board[pX][pY].setCell("Void");
		    			partidaTerminada=true;
		    			board[x][y].setMuerto(bomberman);
		    		}
		    		else if(!board[x][y].is("Enemy")) {
		    			board[pX][pY].setCell("Void");
	                    board[x][y].setCell("Enemy");
	                    moved = true;
		    		}    
		    	}
		    		
		    }
		 }
	}
	
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY, int pNumBlocks) {
	    if (!detectarBomberman(pX, pY)) 
	        board[pX][pY].setCell("Explosion");
	    
	    for (int x = 1; x <= pNumBlocks; x++) {
	        if (pX + x < 17) {
	            if (board[pX + x][pY].is("Hard")) break;
	            else if (!detectarBomberman(pX + x, pY))
	                board[pX + x][pY].setCell("Explosion");
	        }
	        if (pX - x >= 0) {
	            if (board[pX - x][pY].is("Hard")) break;
	            else if (!detectarBomberman(pX - x, pY)) 
	                board[pX - x][pY].setCell("Explosion");
	        }
	    }
	    
	    for (int y = 1; y <= pNumBlocks; y++) {
	        if (pY + y < 11) {
	            if (board[pX][pY + y].is("Hard"))break;
	            else if (!detectarBomberman(pX, pY + y)) 
	                board[pX][pY + y].setCell("Explosion");
	        }
	        if (pY - y >= 0) {
	            if (board[pX][pY - y].is("Hard")) break;
	            else if (!detectarBomberman(pX, pY - y))
	                board[pX][pY - y].setCell("Explosion");
	        }
	    }
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		if(xBM==pX && yBM==pY) {
			board[pX][pY].setMuerto(bomberman);
			return partidaTerminada=true;
		}
		return partidaTerminada;
	}
	
	public void quitarExplosion(int pX, int pY) {
		board[pX][pY].setCell("Void");
	}
	
	public void colocarBomba(int pX, int pY) {
		if(bombasBM > 0) {
			board[pX][pY].setCell(typeBomb);
			bombasBM--;
			TimerModelTool.getTimerModelTool().addBomb();
		}	
	}
	
	public void addBomb() {
		bombasBM++;
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