package model.bombs;

import model.interfaceCell.InterfaceCell;

public abstract class Bomb implements InterfaceCell{

   protected Bomb(int pX, int pY) {}
   
   @Override
   public boolean is(String pType){
	   return pType=="Bomb";
   }
   
   @Override
   public boolean canMove() {
	   return false;
	}
   
   @Override
   public int getId() {
	   return 5;
   }
   
   @Override
   public boolean kills() {
	   return false;
   }
}

