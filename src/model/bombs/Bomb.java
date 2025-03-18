package model.bombs;
import model.Cell;
import model.InterfaceCell;

public abstract class Bomb implements InterfaceCell{

    protected Bomb(int pX, int pY) {}
       
   protected abstract void tiempo(int pX,int pY);
   
   @Override
   public boolean is(String pType){
	   return pType=="Bomb";
   }
   
   @Override
   public boolean canMove() {
	   return false;
	}
}

