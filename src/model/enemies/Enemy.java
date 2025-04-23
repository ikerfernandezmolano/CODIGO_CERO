package model.enemies;
import java.util.Timer;
import model.interfaceCell.InterfaceCell;

public abstract class Enemy implements InterfaceCell{
	Timer timer=null;
	
	protected Enemy(int pX,int pY) {
		
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Enemy";
	}
	
	@Override
	public boolean kills() {
		return true;
	}
	
	protected abstract void moverse(int pX,int pY);
	
	public void stopTimer() {
		if (timer != null) {
            timer.cancel();
            timer.purge();
        }
		timer=null;
	}
}