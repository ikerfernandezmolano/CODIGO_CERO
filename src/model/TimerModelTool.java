package model;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerModelTool {
    private static final TimerModelTool myTMT = new TimerModelTool();
    private final Random r= new Random();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    private TimerModelTool() {}

    public static TimerModelTool getTimerModelTool() {
        return myTMT;
    }

    public void addBomb() {
        scheduler.schedule(() -> GameModel.getGameModel().addBomb(), 4, TimeUnit.SECONDS);
    }

    public void waitForBoss() {
        scheduler.schedule(() -> {
            while(!GameModel.getGameModel().checkFlag(GameModel.getGameModel().posBoolean("BOSS_CREATED"))) {
                int x=r.nextInt(0,GameModel.getGameModel().getSize("WIDTH"));
                int y=r.nextInt(0,GameModel.getGameModel().getSize("HEIGHT"));
                if(!GameModel.getGameModel().isPosition("Bomberman",x,y)){
                    GameModel.getGameModel().changeFlagStatus(GameModel.getGameModel().posBoolean("BOSS_CREATED"),true);
                    GameModel.getGameModel().setCell("Boss",x,y);
                    break;
                }
            }
        }, 3, TimeUnit.SECONDS);
    }

    public void tiempoExplotar(int pX, int pY, int pDist, int pDir) {
        scheduler.schedule(() -> GameModel.getGameModel().explotar(pX, pY, pDist, pDir), 3, TimeUnit.SECONDS);
    }

    public void stop(int pSec) {
        scheduler.schedule(() -> System.exit(0), pSec, TimeUnit.SECONDS);
    }

    public void bossBombs(int pX, int pY) {
        scheduler.schedule(() -> {
            for (int i = 0; i < 5; i++) {
                int type = r.nextInt(0, 5);
                int x = r.nextInt(0, GameModel.getGameModel().getSize("WIDTH"));
                int y = r.nextInt(0, GameModel.getGameModel().getSize("HEIGHT"));
                if (GameModel.getGameModel().is("Void",x,y)) {
                    if (type < 3 && !(Math.abs(x - pX) == Math.abs(y - pY) && Math.abs(x - pX) <= 3)) {
                        GameModel.getGameModel().setCell("Bossy",x,y);
                    } else if (type == 3 && !(Math.abs(x - pX) + Math.abs(y - pY) == 1)) {
                        GameModel.getGameModel().setCell("Super",x,y);
                    } else if (type == 4) {
                        GameModel.getGameModel().setCell("Ultra",x,y);
                    }
                }
            }
        }, 3, TimeUnit.SECONDS);
    }
}