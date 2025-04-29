package model.enemies;
import java.util.Timer;
import model.interfaceCell.InterfaceCell;

public abstract class Enemy implements InterfaceCell{
	private Timer timer=new Timer();
	
	protected Enemy() {}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType.equals("Enemy");
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

	protected Timer getTimer(){
		return timer;
	}
}