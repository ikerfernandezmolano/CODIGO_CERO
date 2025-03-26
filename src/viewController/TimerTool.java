package viewController;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTool {
	private static TimerTool myTimer=new TimerTool();
	private Timer timer=null;
	
	private TimerTool() {}
	
	public static TimerTool getTimerTool() {
		return myTimer;
	}
	
	public void stop(int pSec) {
		timer = new Timer();
   		TimerTask timerTask = new TimerTask() {
   			@Override
   			public void run() {
   				System.exit(0);
    		}		
    	};
   		timer.schedule(timerTask, (int)pSec*1000); 
	}
}
