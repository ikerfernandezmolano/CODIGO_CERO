package model;

import java.util.Observable;

import model.bomberman.Bomberman;
import model.enemies.Enemy;
import model.interfaceCell.CellFactoriesFactory;
import model.interfaceCell.InterfaceCell;

public class Cell extends Observable{
	private int x,y;
	private InterfaceCell cell;
	
	public Cell(int pX, int pY) {
		this.x=pX;
		this.y=pY;
		this.cell=CellFactoriesFactory.getCellFactoriesFactory().generate("Void", pX, pY);
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
	
	public boolean kills() {
		return cell.kills();
	}
	
	public void stopTimer() {
		if(cell.is("Enemy")) ((Enemy)cell).stopTimer();
	}
	
	public int getBombs() {
		if(cell.is("Bomberman")) return ((Bomberman)cell).getBombs();
		return -1;
	}
	
	public void reloadSkin() {
		setChanged();
		notifyObservers(new int[] {cell.getId()});
	}
	
	public String typeBombs() {
		if(cell.is("Bomberman")) return ((Bomberman)cell).getTypeBombs();
		return null;
	}
	
	public void setMuerto(String pType) {
		int n=0;
		switch(pType){
			case "White":
				n=71;
				break;
			case "Black":
				n=72;
				break;
			case "Boss":
				n=32;
				break;
		}
		setChanged();
		notifyObservers(new int[] {n});
	}	
}