package viewController;

import java.util.Timer;
import java.util.TimerTask;

import model.GameModel;

public class TimerTool {
    private static TimerTool myTT = new TimerTool();
    private Timer timer = null;

    private TimerTool() {}

    public static TimerTool getTimerTool() {
        return myTT;
    }

    public void stop(int pSec) {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(timerTask, (int) pSec * 1000);
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

    public void stopEnemyMovement() {
        if (timer != null) {
            timer.cancel(); 
            timer = null;
        }
    }
}