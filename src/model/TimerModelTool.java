package model;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerModelTool {
    private static final TimerModelTool myTMT = new TimerModelTool();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    private TimerModelTool() {}

    public static TimerModelTool getTimerModelTool() {
        return myTMT;
    }

    public void addBomb() {
        scheduler.schedule(() -> GameModel.getGameModel().addBomb(), 4, TimeUnit.SECONDS);
    }

    public void waitForBoss() {
        scheduler.schedule(() -> GameModel.getGameModel().createBoss(), 3, TimeUnit.SECONDS);
    }

    public void tiempoExplotar(int pX, int pY, int pDist, int pDir) {
        scheduler.schedule(() -> GameModel.getGameModel().explotar(pX, pY, pDist, pDir), 3, TimeUnit.SECONDS);
    }

    public void stop(int pSec) {
        scheduler.schedule(() -> System.exit(0), pSec, TimeUnit.SECONDS);
    }

    public void bossBombs(int pX, int pY) {
        scheduler.schedule(() -> {
            Random r = new Random();
            for (int i = 0; i < 5; i++) {
                int type = r.nextInt(0, 5);
                int x = r.nextInt(0, 17);
                int y = r.nextInt(0, 11);
                if (x != pX && y != pY) {
                    if (type < 3 && !(Math.abs(x - pX) == Math.abs(y - pY) && Math.abs(x - pX) <= 3)) {
                        GameModel.getGameModel().colocarBomba(x, y, "Bossy");
                    } else if (type == 3 && !(Math.abs(x - pX) + Math.abs(y - pY) == 1)) {
                        GameModel.getGameModel().colocarBomba(x, y, "Super");
                    } else if (type == 4 && x != pX && y != pY) {
                        GameModel.getGameModel().colocarBomba(x, y, "Ultra");
                    }
                }
            }
        }, 3, TimeUnit.SECONDS);
    }
}