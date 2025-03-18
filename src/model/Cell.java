package model;

import java.util.Observable;

import model.blocks.BlockFactory;
import model.bomberman.BombermanFactory;
import model.bombs.BombFactory;
import model.enemies.EnemieFactory;
import model.explosion.ExplosionFactory;

public class Cell extends Observable{
	private int x,y; 
	private InterfaceCell cell;
	
	public Cell(int pX, int pY) {
		this.x=pX;
		this.y=pY;
		this.cell=BlockFactory.getBlockFactory().generate("Void", pX, pY);
	}
	
	public void setCell(String pType) {
		int pIDType=0;
		if(pType=="Void") {
			pIDType=0;
			cell=BlockFactory.getBlockFactory().generate("Void", x, y);
		}
		else if(pType=="Soft") {
			pIDType=1;
			cell = BlockFactory.getBlockFactory().generate("Soft", x, y);
		}
		else if(pType=="Hard") {
			pIDType=2;
			cell = BlockFactory.getBlockFactory().generate("Hard", x, y);
		}
		else if(pType=="Enemie") {
			pIDType=3;
			cell = EnemieFactory.getEnemieFactory().generate("Default", x, y);
		}
		else if(pType=="White") {
			pIDType=4;
			cell = BombermanFactory.getBombermanFactory().generate("White", x, y);
		}
		else if(pType=="Super") {
			pIDType=5;
			cell = BombFactory.getBombFactory().generate("Super", x, y);
		}
		else if(pType=="Explosion") {
			pIDType=6;
			cell = ExplosionFactory.getExplosionFactory().generate("Default", x, y);
		}
		setChanged();
		notifyObservers(new int[] {pIDType});
		
	}
	
	public boolean canMove() {
		return cell.canMove();
	}
	
	public boolean is(String pType) {
		return cell.is(pType);
	}
	
	public void setMuerto() {
		setChanged();
		notifyObservers(new int[] {7});
	}
}