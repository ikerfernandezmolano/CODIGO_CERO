package model.gameMap;

public class GameMapFactory {
	
	private static GameMapFactory miGMF=new GameMapFactory();
	
	private GameMapFactory() {}

	public static GameMapFactory getGameMapFactory() {
		return miGMF;
	}
	
	public GameMap generate(int pType) {
		GameMap gm=null;
		if(pType==1) gm=new GameMap1();
		else if(pType==2) gm=new GameMap2();
		else if(pType==3) gm=new GameMap3();
		return gm;
	}
}
