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
	private List<int[]> enemigos = new ArrayList<>();
	
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
				else if(type==3) {
					board[i][j].setCell("Enemy");
					agregarEnemigo(i, j);
				}
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
			board[pX][pY].setCell(typeBomb);
		else board[pX][pY].setCell("Void");
	}
	
//-----------------------ENEMIES--------------------------------


	private synchronized void agregarEnemigo(int x, int y) {
	    enemigos.add(new int[]{x, y});
	}

	public synchronized void actualizarEnemigos() {
		if(enemigos.size()==0) {
			partidaTerminada=true;
		}
		else {
			for (int i = 0; i < enemigos.size(); i++) {
		        int[] pos = enemigos.get(i);
		        int x = pos[0], y = pos[1];

		        if (board[x][y].is("Enemy")) {
		            int[] nuevaPosicion = moverEnemigos(x, y);

		            enemigos.set(i, nuevaPosicion);
		        } else {

		            enemigos.remove(i);
		            i--; 
		        }
		    }
		}
		
	}


	private synchronized int[] moverEnemigos(int pX, int pY) {
	    Random r = new Random();
	    boolean moved = false;
	    int newX = pX, newY = pY;

	    for (int intentos = 0; intentos < 15 && !moved; intentos++) {
	        int n = r.nextInt(4); 
	        newX = pX;
	        newY = pY;

	        if (n < 2) {
	            newX = (n == 0) ? newX + 1 : newX - 1; 
	        } else {
	            newY = (n == 2) ? newY + 1 : newY - 1; 
	        }

	        if (puedeMoverse(newX, newY)) {
	            synchronized (board) {
	                if (board[newX][newY].is("Bomberman")) {
	                    board[pX][pY].setCell("Void"); 
	                    partidaTerminada = true; 
	                    board[newX][newY].setMuerto(bomberman); 
	                }

	                else if (!board[newX][newY].is("Enemy")) {
	                    board[pX][pY].setCell("Void"); 
	                    board[newX][newY].setCell("Enemy"); 
	                    moved = true; 
	                }
	            }
	        }
	    }

	    // Devolver la nueva posición (puede ser igual a la original si no se movió)
	    return new int[]{newX, newY};
	}
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY, int pNumBlocks) {
	    if (!detectarBomberman(pX, pY)) 
	        board[pX][pY].setCell("Explosion");

	    for (int x = 1; x <= pNumBlocks; x++) {
	        if (pX + x < 17) {
	            if (!board[pX + x][pY].is("Hard")) {
	                if (!detectarBomberman(pX + x, pY))
	                    board[pX + x][pY].setCell("Explosion");
	            }
	        }

	        if (pX - x >= 0) {
	            if (!board[pX - x][pY].is("Hard")) {
	                if (!detectarBomberman(pX - x, pY))
	                    board[pX - x][pY].setCell("Explosion");
	            }
	        }
	    }

	    for (int y = 1; y <= pNumBlocks; y++) {
	        if (pY + y < 11) {
	            if (!board[pX][pY + y].is("Hard")) {
	                if (!detectarBomberman(pX, pY + y))
	                    board[pX][pY + y].setCell("Explosion");
	            }
	        }

	        if (pY - y >= 0) {
	            if (!board[pX][pY - y].is("Hard")) {
	                if (!detectarBomberman(pX, pY - y))
	                    board[pX][pY - y].setCell("Explosion");
	            }
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