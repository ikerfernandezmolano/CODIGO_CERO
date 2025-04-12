package model.interfaceCell;

import model.blocks.BlockFactory;
import model.bomberman.BombermanFactory;
import model.bombs.BombFactory;
import model.enemies.EnemyFactory;
import model.explosion.ExplosionFactory;

public class CellFactoriesFactory {
	private static CellFactoriesFactory myCFF=new CellFactoriesFactory();
	
	private CellFactoriesFactory() {}
	
	public static CellFactoriesFactory getCellFactoriesFactory() {
		return myCFF;
	}
	
	public InterfaceCell generate(String pType, int pX, int pY) {
		InterfaceCell ic=null;
		if(pType=="Void")
			ic=BlockFactory.getBlockFactory().generate("Void", pX, pY);
		else if(pType=="Soft") 
			ic = BlockFactory.getBlockFactory().generate("Soft", pX, pY);
		else if(pType=="Hard") 
			ic = BlockFactory.getBlockFactory().generate("Hard", pX, pY);
		else if(pType=="Enemy")
			ic = EnemyFactory.getEnemieFactory().generate("Default", pX, pY);
		else if(pType=="White")
			ic = BombermanFactory.getBombermanFactory().generate("White", pX, pY);
		else if(pType=="Black")
			ic = BombermanFactory.getBombermanFactory().generate("Black", pX, pY);
		else if(pType=="Super")
			ic = BombFactory.getBombFactory().generate("Super", pX, pY);
		else if(pType=="Ultra")
			ic = BombFactory.getBombFactory().generate("Ultra", pX, pY);
		else if(pType=="Explosion") 
			ic = ExplosionFactory.getExplosionFactory().generate("Default", pX, pY);
		return ic;
	}

}
