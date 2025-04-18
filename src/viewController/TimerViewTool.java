package viewController;

import java.util.Timer;
import java.util.TimerTask;

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
}