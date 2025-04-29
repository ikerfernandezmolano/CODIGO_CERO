package viewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.Timer;
import javax.swing.SwingUtilities;

public class TimerViewTool {
    private static TimerViewTool myTVT = new TimerViewTool();
    private java.util.Timer timer = null;

    // ---------- NUEVOS CAMPOS PARA CRONÓMETRO ----------
    private Timer chronoTimer = null;
    private int elapsedSeconds = 0;
    private TimeListener timeListener = null;

    public interface TimeListener {
        void onTimeUpdate(int minutes, int seconds);
    }
    // ---------------------------------------------------

    private TimerViewTool() {}

    public static TimerViewTool getTimerViewTool() {
        return myTVT;
    }

    public void stop(int pSec) {
        timer = new java.util.Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        timer.schedule(timerTask, pSec * 1000);
    }

    // ---------------- NUEVOS MÉTODOS ------------------

    public void startChrono(TimeListener listener) {
        this.timeListener = listener;
        elapsedSeconds = 0;

        if (chronoTimer != null) chronoTimer.stop();

        chronoTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedSeconds++;
                int minutes = elapsedSeconds / 60;
                int seconds = elapsedSeconds % 60;

                if (timeListener != null) {
                    SwingUtilities.invokeLater(() -> {
                        timeListener.onTimeUpdate(minutes, seconds);
                    });
                }
            }
        });
        chronoTimer.start();
    }

    public void stopChrono() {
        if (chronoTimer != null) chronoTimer.stop();
    }

    public void resetChrono() {
        stopChrono();
        elapsedSeconds = 0;
    }

    public int getElapsedMinutes() {
        return elapsedSeconds / 60;
    }

    public int getElapsedSeconds() {
        return elapsedSeconds % 60;
    }
    // --------------------------------------------------
}
