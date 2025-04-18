package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimerModelTool {
	private static TimerModelTool myTMT=new TimerModelTool();
	private Timer timer=new Timer();
	
	private TimerModelTool() {}
	
	public static TimerModelTool getTimerModelTool() {
		return myTMT;
	}
    
    public void addBomb() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().addBomb();
            }
        };
        timer.schedule(timerTask, 4000);
    }
    
    public void tiempoExplotar(int pX,int pY, int pDist) {
  		TimerTask timerTask = new TimerTask() {
  			@Override
  			public void run() {
  				GameModel.getGameModel().explotar(pX, pY,pDist);
  			}		
  		};
  		timer.schedule(timerTask, 3000);
   }
    
    public void stop(int pSec) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(timerTask, pSec * 1000);
    }
}
