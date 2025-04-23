package model;

import java.util.Random;
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
    
    public void tiempoExplotar(int pX,int pY, int pDist, int pDir) {
  		TimerTask timerTask = new TimerTask() {
  			@Override
  			public void run() {
  				GameModel.getGameModel().explotar(pX, pY,pDist,pDir);
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
    
    public void bossBombs(int pX,int pY) {
    	TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
            	Random r=new Random();
            	for(int i=0;i<5;i++) {
	            	int type=r.nextInt(0,5);
	            	int x=r.nextInt(0, 17);
	            	int y=r.nextInt(0, 11);
	            	if(x!=pX && y!=pY) {
	            		if (type < 3 && !(Math.abs(x - pX) == Math.abs(y - pY) && Math.abs(x - pX) <= 3)) {
	            		    GameModel.getGameModel().colocarBomba(x, y, "Bossy");
	            		} else if (type == 3 && !(Math.abs(x - pX) + Math.abs(y - pY) == 1)) {
		            		GameModel.getGameModel().colocarBomba(x, y, "Super");
		            	} else if(type==4 && x!=pX && y!=pY) {
		            		GameModel.getGameModel().colocarBomba(x, y, "Ultra");
		            	}
	            	}
            	}
            }
        };
        timer.schedule(timerTask, 3000);
    }
}
