package model;

import java.util.Observable;
import java.util.Random;

import model.gameMap.GameMap;
import model.gameMap.GameMapFactory;

public class GameModel extends Observable{
	private static GameModel myGM=new GameModel();
	private Cell[][] board;
	private boolean partidaTerminada;
	
	private int xBM, yBM;
	private String bomberman;
	private int bombasBM;
	private String typeBomb;
	
	private int numEnemies=0;
	
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
		setChanged();
		notifyObservers();
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
				else if(type==3) {
					board[i][j].setCell("Enemy");
					numEnemies++;
				}
				else if(type==4) {
					board[i][j].setCell(bomberman);
					bombasBM=board[i][j].getBombs();
					typeBomb=board[i][j].typeBombs();
				}
			}
		}
	}

//------------------------BOMBERMAN--------------------------	
	
	public boolean moverseBM(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			xBM=pXn;
			yBM=pYn;
			moverseConBomba(pXact,pYact);
			if(board[pXn][pYn].kills()) {
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
	
	private void moverseConBomba(int pX,int pY) {
		if(board[pX][pY].is("Bomb")) 
			board[pX][pY].reloadSkin();
		else board[pX][pY].setCell("Void");
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		if(xBM==pX && yBM==pY) {
			board[pX][pY].setMuerto(bomberman);
			return partidaTerminada=true;
		}
		return partidaTerminada;
	}
	
//-----------------------ENEMIES--------------------------------


	public void moverseEnemigo(int pX, int pY) {
		Random r=new Random();
		boolean moved=false;
		while(!moved) {
			int n=r.nextInt(4);
			int x=pX,y=pY;
			if(n<2) x = (n==0) ? x+1:x-1;
			else y = (n==2) ? y+1:y-1;
			if(x>=0 && x<17 && y>=0 && y<11) {
				if(board[x][y].canMove() && !board[x][y].is("Enemy")) {
					if(board[x][y].is("Explosion")) {
						board[pX][pY].stopTimer();
						numEnemies--;
						if(numEnemies<=0) TimerModelTool.getTimerModelTool().stop(2);
						board[pX][pY].setCell("Void");
					} else {
						board[pX][pY].setCell("Void");
						detectarBomberman(x, y);
						board[x][y].setCell("Enemy");
					}
					moved=true;
				}
			}
		}
	}
	
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY, int pNumBlocks) {
	    if (!detectarBomberman(pX, pY))
	        board[pX][pY].setCell("Explosion");
	    for (int x = 1; x<17 && x <= pNumBlocks; x++) {
	        if (pX + x < 17) {
	            if (board[pX + x][pY].is("Hard")) break;
	            explosion(pX+x,pY);
	        }
	
	        if (pX - x >= 0) {
	            if (board[pX - x][pY].is("Hard")) break;
	            explosion(pX-x,pY);
	        }
	        if(numEnemies<=0) TimerModelTool.getTimerModelTool().stop(1);
	    }
	    for (int y = 1; y<11 && y <= pNumBlocks; y++) {
	        if (pY + y < 11) {
	            if (board[pX][pY + y].is("Hard")) break;
	            explosion(pX,pY+y);
	        }
	        if (pY - y >= 0) {
	            if (board[pX][pY - y].is("Hard")) break;
	            explosion(pX,pY-y);
	        }
	        if(numEnemies<=0) TimerModelTool.getTimerModelTool().stop(1);
	    }
	    
	}
	
	private void explosion(int pX, int pY) {
		if (board[pX][pY].is("Enemy")) {
            board[pX][pY].stopTimer();
            board[pX][pY].setCell("Explosion");
            numEnemies--;
        } else if (!detectarBomberman(pX, pY))
            board[pX][pY].setCell("Explosion");
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