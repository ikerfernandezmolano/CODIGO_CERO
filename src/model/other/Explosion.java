package model.other;
import java.util.Timer;
import java.util.TimerTask;

import model.Cell;
import model.InterfaceCell;
import model.Tablero;

public class Explosion implements InterfaceCell{
	private Timer timer=null;
	
	public Explosion (int pX, int pY) {	
		tiempo(pX,pY);
    }
	
	private void tiempo(int pX, int pY) {
		timer = new Timer();
   		TimerTask timerTask = new TimerTask() {
   			@Override
   			public void run() {
   				Tablero.getTablero().quitarExplosion(pX,pY);
    		}		
    	};
   		timer.schedule(timerTask, 2000); 
	}

	@Override
	public boolean canMove() {
		return true;
	}

	@Override
	public boolean is(String pType) {
		return pType=="Explosion";
	}
}

