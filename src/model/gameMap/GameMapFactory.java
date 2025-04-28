package model.gameMap;

public class GameMapFactory {
	private static GameMapFactory myGMF=new GameMapFactory();
	
	private GameMapFactory() {}
	
	public static GameMapFactory getGameMapFactory() {
		return myGMF;
	}
	
	public GameMap generate(int pType, int pWidth, int pHeight) {
		GameMap gm=null;
		if(pType==1) gm=new GameMap1(pWidth,pHeight);
		else if(pType==2) gm=new GameMap2(pWidth,pHeight);
		else if(pType==3) gm=new GameMap3(pWidth,pHeight);
		else if(pType==4) gm=new GameMap4(pWidth,pHeight);
		return gm;
	}
}
