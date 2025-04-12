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
	
	public boolean mata() {
		return cell.mata();
	}
	
	public int getBombs() {
		if(cell.is("Bomberman")) return ((Bomberman)cell).getBombs();
		return -1;
	}
	
	public String typeBombs() {
		if(cell.is("Bomberman")) return ((Bomberman)cell).getTypeBombs();
		return null;
	}
	
	public void setMuerto(String pBomberman) {
		setChanged();
		notifyObservers(new int[] {(pBomberman=="White") ? 71 : 72});
	}	
}