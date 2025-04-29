package model;

import java.util.Observable;
import java.util.Random;

import model.gameMap.GameMap;
import model.gameMap.GameMapFactory;
import model.interfaceCell.CellFactoriesFactory;

public class GameModel extends Observable{
	private static GameModel myGM=new GameModel();
	
	private Cell[][] board;
	private static final int BOARD_WIDTH=17;
	private static final int BOARD_HEIGHT=11;
	
	private byte gameStatus= 2;
	public static final int FINISHED_GAME=1;
	public static final int END_EXPLOSION=2;
	public static final int BOSS_CREATED=3;
	public static final int POWERUP_IN_MAP=4;
	public static final int BOMBERMAN_POWERUP=5;
	
	private GameMap map;
	
	private final Random r=new Random();
	
	private final Position posBM=new Position();
	private final Position posPU=new Position();
	
	private String bomberman;
	private int bombasBM;
	private String typeBomb;
	private int numEnemies=0;
	private int bossHealth=3;
	
	private GameModel() {
		initialize();
	}
	
	public static GameModel getGameModel() {
		return myGM;
	}
	
	public void configurarJuego(String pBomberman, int pMap) {
		bomberman=pBomberman;
		map=GameMapFactory.getGameMapFactory().generate(pMap,BOARD_WIDTH,BOARD_HEIGHT);
		setChanged();
		notifyObservers(new int[] {BOARD_WIDTH,BOARD_HEIGHT});
	}

	private void initialize() {
		board = new Cell[BOARD_WIDTH][BOARD_HEIGHT];
		for(int i=0;i<BOARD_WIDTH;i++) {
			for(int j=0;j<BOARD_HEIGHT;j++) {
				board[i][j]=new Cell(i, j);
			}
		}
	}
	
//--------------------TABLERO_CREATE-------------------------------	
	
	public void crearTablero() {
		int[][] tab=new int[BOARD_WIDTH][BOARD_HEIGHT];
		map.createBoard(tab);
		for(int i=0;i<tab.length;i++) {
			for(int j=0;j<tab[i].length;j++) {
				switch(tab[i][j]) {
					case 1:
						board[i][j].setCell("Soft");
						break;
					case 2:
						board[i][j].setCell("Hard");
						break;
					case 3:
						board[i][j].setCell("Enemy");
						numEnemies++;
						break;
					case 4:
						board[i][j].setCell(bomberman);
						bombasBM=board[i][j].getBombs();
						typeBomb=board[i][j].typeBombs();
						break;
				}
			}
		}
		numEnemies = numEnemies;
		CellFactoriesFactory.getCellFactoriesFactory().generate("ExtraLife", posPU.getX(), posPU.getY());
	}

//------------------------BOMBERMAN--------------------------	
	
	public boolean moverseBM(int pXact,int pYact, int pX,int pY) {
		boolean puede=puedeMoverse(pX,pY);
		if (puede) {
			posBM.changePosition(pX, pY);
			moverseConBomba(pXact,pYact);
			if(board[pX][pY].kills()) {
				if(checkFlag(BOMBERMAN_POWERUP)) {
					changeFlagStatus(BOMBERMAN_POWERUP,false);
					if(board[pX][pY].is("Enemy"))numEnemies--;
					board[pX][pY].setCell(bomberman);
				}else{
					changeFlagStatus(FINISHED_GAME,true);
					board[pX][pY].setMuerto(bomberman);
				    board[pXact][pYact].setCell("Void");
				}
			}else if(board[pX][pY].is("PowerUp")) {
				board[pX][pY].setCell(bomberman);
				changeFlagStatus(BOMBERMAN_POWERUP,true);
			}
		    else board[pX][pY].setCell(bomberman);
		}
		return puede;
	}
	
	private boolean puedeMoverse(int pX, int pY) {
		if(pX<0 || pX>=BOARD_WIDTH || pY<0 || pY>=BOARD_HEIGHT) return false;
		return board[pX][pY].canMove();
	}
	
	private void moverseConBomba(int pX,int pY) {
		if(board[pX][pY].is("Bomb")) {
			if(checkFlag(BOMBERMAN_POWERUP) && checkFlag(END_EXPLOSION)) {
				changeFlagStatus(BOMBERMAN_POWERUP,false);
				board[pX][pY].setCell("Void");
			}
			board[pX][pY].reloadSkin();
		}else board[pX][pY].setCell("Void");
	}
	
	private boolean detectarBomberman(int pX, int pY) {
		if(posBM.isPosition(pX, pY)) {
			if(!checkFlag(BOMBERMAN_POWERUP)) {
				board[pX][pY].setMuerto(bomberman);
				changeFlagStatus(FINISHED_GAME,true);
			}
			return true;
		}
		return checkFlag(FINISHED_GAME);
	}
	
//-----------------------ENEMIES--------------------------------

	public void moverseEnemigo(int pX, int pY) {
		boolean moved=false;
		while(!moved) {
			int n=r.nextInt(4);
			int x=pX,y=pY;
			if(n<2) x = (n==0) ? x+1:x-1;
			else y = (n==2) ? y+1:y-1;
			if(x>=0 && x<BOARD_WIDTH && y>=0 && y<BOARD_HEIGHT) {
				if(board[x][y].canMove() && !board[x][y].is("Enemy") && !board[x][y].is("PowerUp")) {
					if(board[x][y].is("Explosion")) {
						board[pX][pY].stopTimer();
						numEnemies--;
						if(numEnemies<=0) TimerModelTool.getTimerModelTool().waitForBoss();
						board[pX][pY].setCell("Void");
					} else {
						board[pX][pY].setCell("Void");
						if(detectarBomberman(x, y)) {
							if(checkFlag(BOMBERMAN_POWERUP)) {
								changeFlagStatus(BOMBERMAN_POWERUP,false);
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
	
//------------------------BOMBS-------------------------------	
	
	public void explotar(int pX, int pY, int pNumBlocks, int pDir) {
	    if (pDir==0) explotarHV(pX,pY,pNumBlocks);
	    else  explotarDiag(pX,pY,pNumBlocks);
	}
	
	private void explotarHV(int pX, int pY, int pNumBlocks){
		if (!detectarBomberman(pX, pY))
	        board[pX][pY].setCell("Explosion");
	    for (int x = 1; x<BOARD_WIDTH && x <= pNumBlocks; x++) {
	        if (pX + x < BOARD_WIDTH) {
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
	    for (int y = 1; y<BOARD_HEIGHT && y <= pNumBlocks; y++) {
	        if (pY + y < BOARD_HEIGHT) {
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
			if (pX + i < BOARD_WIDTH && pY + i < BOARD_HEIGHT) {
	            if (board[pX + i][pY + i].is("Hard")) break;
	            explosion(pX+i,pY + i);
	        }
	
	        if (pX - i >= 0 && pY - i >= 0) {
	            if (board[pX - i][pY - i].is("Hard")) break;
	            explosion(pX-i,pY - i);
	        }
			if (pX -i >= 0 && pY + i < BOARD_HEIGHT) {
	            if (board[pX -i][pY + i].is("Hard")) break;
	            explosion(pX-i,pY+i);
	        }
			if (pX + i <BOARD_WIDTH && pY - i >= 0) {
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
        	if(checkFlag(BOMBERMAN_POWERUP)) {
        		changeFlagStatus(BOMBERMAN_POWERUP,false);
        		board[pX][pY].setCell(bomberman);
        	}
        }
	}
	
	public void colocarBomba(int pX, int pY) {
		if(bombasBM > 0) {
			changeFlagStatus(END_EXPLOSION,false);
			board[pX][pY].setCell(typeBomb);
			bombasBM--;
			TimerModelTool.getTimerModelTool().addBomb();
		}	
	}
	
	public void addBomb() {
		bombasBM++;
	}

//------------------------POSITION-------------------------------

	public void changePosition(String pPosition, int pX, int pY){
		if(pPosition.equals("Bomberman")) posBM.changePosition(pX,pY);
		else if(pPosition.equals("PowerUp")) posPU.changePosition(pX,pY);
	}

	public int getCoord(String pPosition, char pAxis){
		int axis=-1;
		if(pPosition.equals("Bomberman")) axis = (pAxis=='X') ? posBM.getX():posBM.getY();
		else if(pPosition.equals("PowerUp")) axis = (pAxis=='X') ? posPU.getX():posPU.getY();
		return axis;
	}

	public boolean isPosition(String pPosition,int pX, int pY){
		if(pPosition.equals("Bomberman") && posBM.getX()==pX && posBM.getY()==pY) return true;
		else if(pPosition.equals("PowerUp") && posPU.getX()==pX && posPU.getY()==pY) return true;
		return false;
	}

//------------------------STATUS--------------------------------
	
	public void changeFlagStatus(int pPos,boolean pValue) {
		gameStatus=(pValue)? (byte)(gameStatus|(1<<pPos-1)) : (byte)(gameStatus&~(1<<pPos-1));
	}
	
	public boolean checkFlag(int pPos) {
		return (gameStatus & (1<<pPos-1))!=0;
	}
	
	public boolean getEstadoPartida() {
		return checkFlag(FINISHED_GAME);
	}
//
	public int getSize(String pType){
		switch(pType){
			case "WIDTH":
				return BOARD_WIDTH;
            case "HEIGHT":
				return BOARD_HEIGHT;
		}
		return -1;
	}
	
//------------------------CELLS-------------------------------

	public void setCell(String pType, int pX, int pY) { board[pX][pY].setCell(pType); }
	public boolean is(String pType, int pX, int pY) { return board[pX][pY].is(pType); }
	public Cell getCell(int pX,int pY) { return board[pX][pY]; }
}