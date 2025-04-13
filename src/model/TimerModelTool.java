package model;

import java.util.Timer;
import java.util.TimerTask;

public class TimerModelTool {
	private static TimerModelTool myTMT=new TimerModelTool();
	private Timer timer=null;
	
	private TimerModelTool() {}
	
	public static TimerModelTool getTimerModelTool() {
		return myTMT;
	}
    
    public void addBomb() {
    	timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().addBomb();
            }
        };
        timer.schedule(timerTask, 4000);
    }
    
    public void startEnemyMovement(int pIntervalMS) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().actualizarEnemigos();
            }
        };
        timer.scheduleAtFixedRate(task, 0, pIntervalMS); 
    }
}
