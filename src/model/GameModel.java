package model;

import java.util.Observable;
import java.util.Random;

import model.gameMap.GameMap;
import model.gameMap.GameMapFactory;
import model.powerUp.PowerUp;

public class GameModel extends Observable{
	private static GameModel myGM=new GameModel();
	private Cell[][] board;
	private boolean partidaTerminada;
	
	private int xBM, yBM;
	private String bomberman;
	private int bombasBM;
	private String typeBomb;
	private boolean finExplosion=true;
	
	private int numEnemies=0;
	private int bossHealth=3;
	private boolean bossCreated=false;
	
	private boolean hasPU=false;
	private boolean BMHasPU=false;
	private int xPU;
	private int yPU;
	
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
		new PowerUp(xPU,yPU);
	}

//------------------------BOMBERMAN--------------------------	
	
	public boolean moverseBM(int pXact,int pYact, int pXn,int pYn) {
		boolean puede=puedeMoverse(pXn,pYn);
		if (puede) {
			xBM=pXn;
			yBM=pYn;
			moverseConBomba(pXact,pYact);
			if(board[pXn][pYn].kills()) {
				if(BMHasPU) {
					BMHasPU=false;
					if(board[pXn][pYn].is("Enemy")){
						numEnemies--;
					}
					board[pXn][pYn].setCell(bomberman);
				}else {
					partidaTerminada=true;
				    board[pXact][pYact].setMuerto(bomberman);
				}
			}else if(board[pXn][pYn].is("PowerUp")) {
				board[pXn][pYn].setCell(bomberman);
				BMHasPU=true;
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
		if(board[pX][pY].is("Bomb")) {
			if(BMHasPU && finExplosion) {
				BMHasPU=false;
				board[pX][pY].setCell("Void");
			}
			board[pX][pY].reloadSkin();
		}else board[pX][pY].setCell("Void");
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		if(xBM==pX && yBM==pY) {
			if(!BMHasPU) {
				board[pX][pY].setMuerto(bomberman);
				return partidaTerminada=true;
			}else {
				return true;
			}
		}
		return partidaTerminada;
	}
	
//-----------------------ENEMIES--------------------------------
	
	public void createBoss() {
		while(!bossCreated) {
			Random r=new Random();
			int x=r.nextInt(0,17);
			int y=r.nextInt(0,11);
			if(x!=xBM && y!=yBM){
				bossCreated=true;
				board[x][y].setCell("Boss");
			}
		}
	}

	public void moverseEnemigo(int pX, int pY) {
		Random r=new Random();
		boolean moved=false;
		while(!moved) {
			int n=r.nextInt(4);
			int x=pX,y=pY;
			if(n<2) x = (n==0) ? x+1:x-1;
			else y = (n==2) ? y+1:y-1;
			if(x>=0 && x<17 && y>=0 && y<11) {
				if(board[x][y].canMove() && !board[x][y].is("Enemy") && !board[x][y].is("PowerUp")) {
					if(board[x][y].is("Explosion")) {
						board[pX][pY].stopTimer();
						numEnemies--;
						if(numEnemies<=0) TimerModelTool.getTimerModelTool().waitForBoss();
						board[pX][pY].setCell("Void");
					} else {
						board[pX][pY].setCell("Void");
						if(detectarBomberman(x, y)) {
							if(BMHasPU) {
								BMHasPU=false;
								board[x][y].setCell(bomberman);
								numEnemies--;
							}else {
								board[x][y].setCell("Enemy");
							}
						}else {
							board[x][y].setCell("Enemy");
						}
					}
					moved=true;
				}
			}
		}
	}
	
	public void moverseBoss(int pX, int pY) {
		Random r=new Random();
		boolean moved=false;
		while(!moved) {
			int x=r.nextInt(0,17);
			int y=r.nextInt(0,11);
			if(x!=xBM && y!=yBM) {
				moved=true;
				board[x][y].setCell("Boss");
				board[pX][pY].setCell("Void");
			}
		}
	}
	
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY, int pNumBlocks, int pDir) {
	    if (pDir==0) explotarHV(pX,pY,pNumBlocks);
	    else explotarDiag(pX,pY,pNumBlocks); 
	}
	
	private void explotarHV(int pX, int pY, int pNumBlocks){
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
	        if(numEnemies<=0) TimerModelTool.getTimerModelTool().waitForBoss();
	        if(bossHealth<=0) TimerModelTool.getTimerModelTool().stop(2);
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
	        if(numEnemies<=0) TimerModelTool.getTimerModelTool().waitForBoss();
	        if(bossHealth<=0) TimerModelTool.getTimerModelTool().stop(2);
	    }
	}
	
	private void explotarDiag(int pX, int pY, int pNumBlocks) {
		if (!detectarBomberman(pX, pY))
	        board[pX][pY].setCell("Explosion");
		for(int i=1;i<= pNumBlocks;i++) {
			if (pX + i < 17 && pY + i < 11) {
	            if (board[pX + i][pY + i].is("Hard")) break;
	            explosion(pX+i,pY + i);
	        }
	
	        if (pX - i >= 0 && pY - i >= 0) {
	            if (board[pX - i][pY - i].is("Hard")) break;
	            explosion(pX-i,pY - i);
	        }
			if (pX -i >= 0 && pY + i < 11) {
	            if (board[pX -i][pY + i].is("Hard")) break;
	            explosion(pX-i,pY+i);
	        }
			if (pX + i <17 && pY - i >= 0) {
	            if (board[pX+i][pY - i].is("Hard")) break;
	            explosion(pX+i,pY-i);
	        }
		}
	}
	
	private void explosion(int pX, int pY) {
		if (board[pX][pY].is("Enemy")) {
			if (board[pX][pY].is("Boss")) bossHealth--;
			else {
	            board[pX][pY].stopTimer();
	            board[pX][pY].setCell("Explosion");
	            numEnemies--;
			}
        } else if (!detectarBomberman(pX, pY))
            board[pX][pY].setCell("Explosion");
        else if(detectarBomberman(pX, pY)) {
        	if(BMHasPU) {
        		BMHasPU=false;
        		board[pX][pY].setCell(bomberman);
        	}
        }
	}
	
	public void quitarExplosion(int pX, int pY) {
		board[pX][pY].setCell("Void");
		finExplosion=true;
	}
	
	public void colocarBomba(int pX, int pY) {
		if(bombasBM > 0) {
			finExplosion=false;
			board[pX][pY].setCell(typeBomb);
			bombasBM--;
			TimerModelTool.getTimerModelTool().addBomb();
		}	
	}
	
	public void colocarBomba(int pX, int pY, String pType) {
		board[pX][pY].setCell(pType);
	}
	
	public void addBomb() {
		bombasBM++;
	}
	
//------------------------POWER_UP----------------------------
	public void colocarPowerUp() {
		if(!BMHasPU) {
			int pX;
			int pY;
			Random r = new Random();
			do {
				pX = r.nextInt(16);
				pY = r.nextInt(10);
			}while(!board[pX][pY].is("Void"));
			
			if (hasPU) {
				board[xPU][yPU].setCell("Void");
			}
			else {
				hasPU=true;
			}
			board[pX][pY].setCell("PowerUp");
			xPU=pX;
			yPU=pY;
		}else {
			new PowerUp(0,0);
		}
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