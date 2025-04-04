package model;

import java.util.Observable;

import model.blocks.BlockFactory;
import model.bomberman.Bomberman;
import model.interfaceCell.CellFactoriesFactory;
import model.interfaceCell.InterfaceCell;

public class Cell extends Observable{
	private int x,y;
	private InterfaceCell cell;
	
	public Cell(int pX, int pY) {
		this.x=pX;
		this.y=pY;
		this.cell=BlockFactory.getBlockFactory().generate("Void", pX, pY);
	}
	
	public void setCell(String pType) {
		cell=CellFactoriesFactory.getCellFactoriesFactory().generate(pType, x, y);
		setChanged();
		notifyObservers(new int[] {cell.getId()});
		
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
	
	public boolean hasBomb() {
		boolean has=false;
		if(cell.getId() == 4) {
			if (((Bomberman)cell).hasBomb()) {
				has=true;
			}
		}
		return has;
	}

}