package viewController;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class TimerViewTool {
    private static TimerViewTool myTVT = new TimerViewTool();
    private Timer timer = null;

    private TimerViewTool() {}

    public static TimerViewTool getTimerViewTool() {
        return myTVT;
    }

    public void stop(int pSec) {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(timerTask, pSec * 1000);
    }
    
    public void startEnemyMovement(int intervalMs) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                GameModel.getGameModel().moverEnemigos();
            }
        };
        timer.scheduleAtFixedRate(task, 0, intervalMs); 
    }
}