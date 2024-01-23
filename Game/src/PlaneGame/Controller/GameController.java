package PlaneGame.Controller;

import PlaneGame.CallBack;
import PlaneGame.Model.GameModel;
import PlaneGame.Object.Enemy;
import PlaneGame.Object.Missile;
import PlaneGame.Object.PlayerPlane;
import PlaneGame.Utils;
import PlaneGame.View.GameView;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    private List<Enemy> enemyList;
    private List<Missile> missileList;
    private PlayerPlane playerPlane;
    private GameModel gameModel;
    private GameView gameView;
    private boolean Pause;//是否暂停

    private CallBack callBack;

    private Integer score;

    private int pMissileInterval = 10;
    private int eMissileInterval = 10;


    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    //GameView监听器调用
   /* public void isPause(boolean pause) {
        Pause = pause;
//        long currentTime = Utils.getCurrentTime();
//        if (pause) {
//            gameModel.setPauseStartTime(currentTime);
//        } else {
//            gameModel.setPauseTime(0);
//        }
    }*/

    public void isPause(boolean pause) {
        Pause = pause;
        if (pause) {
            // 记录暂停时的游戏时间
            gameModel.setGameElapsedTime(Utils.getCurrentTime() - gameModel.getStartTime() - gameModel.getPauseTime());
        } else {
            // 游戏恢复时，重置开始时间，排除暂停时间
            gameModel.setStartTime(Utils.getCurrentTime() - gameModel.getGameElapsedTime());
            gameModel.setPauseTime(0);
        }
    }
    //功能：1.gameView设置鼠标移动监听器 2.更新玩家飞机位置
    //GameController()
    public void updatePlayerPlane() {
        gameView.setFocusable(true);
        gameView.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                playerPlane = gameModel.getPlayerPlane();
                int width = playerPlane.getWidth();
                int height = playerPlane.getHeight();

                playerPlane.setX(x-height/2);
                playerPlane.setY(y-width/2);

                gameModel.updatePlayerPlane(playerPlane);
                gameView.updateGameModel(gameModel);
                gameView.repaint();
            }
        });
    }

    //功能：更新每个导弹的位置（move方法），同时生成新的导弹，此方法在定时器监听器里触发
    //GameView定时器
    public void updateMissileList() {
        for (int i = 0;i < missileList.size();i++) {
            Missile missile = missileList.get(i);
            missile.move();
            if (missile.getY() > 998 || missile.getY() < -10) {
                missileList.remove(i);
            } else {
                missileList.set(i,missile);
            }
        }

        if (pMissileInterval == 0) {
            Missile pMissile = new Missile(playerPlane.getX()+playerPlane.getWidth()/2,playerPlane.getY()-50,10,30,true);
            missileList.add(pMissile);
            pMissileInterval = 4;
        } else {
            pMissileInterval--;
        }

        if (eMissileInterval == 0) {
            for (Enemy enemy : enemyList) {
                Missile eMissile = new Missile(enemy.getX()+enemy.getWidth()/2,enemy.getY()+enemy.getHeight()+10,10,30,false);
                missileList.add(eMissile);
            }
            eMissileInterval = 24;
        } else {
            eMissileInterval--;
        }

//        //潜在Error
//        Missile eMissile = null;
//        for (Enemy enemy : enemyList) {
////            eMissile = new Missile(enemy.getX(),enemy.getY(),40,80);
//            missileList.add(eMissile);
//        }

        gameModel.updateMissileList(missileList);
    }

    //功能：更新每个敌人的位置（move方法），同时判断是否要生成敌人，并相应生成，此方法在定时器监听器里触发
    //GameView定时器
    public void updateEnemyList() {
        for (int i = 0;i < enemyList.size();i++) {
            Enemy enemy = enemyList.get(i);
            enemy.move();
            if (enemy.getY() > 1000) {
                enemyList.remove(i);
            }
        }
        Random random = new Random();
        int max = random.nextInt(2)+2;
        if (enemyList.size() < max) {
            //p1 todo 设置位置随机
            Enemy enemy = new Enemy(0,0,50,50);
            int width = enemy.getWidth();
            int height = enemy.getHeight();
            //todo bound must be positive
            try {
                enemy.setX(new Random().nextInt(random.nextInt(500-width)));
            } catch (Exception e) {
                System.out.println("GameController::updateEnemyList catch:");
                System.out.println("bound:"+(500-width));
//                e.printStackTrace();
            }
            enemy.setY(height*-1-2);
            enemyList.add(enemy);
        }

        gameModel.updateEnemyList(enemyList);
    }

    //功能：调用上述三个方法，更新this.gameModel,先判断是否暂停再更新
    //GameView定时器
    public void updateGameModel() {
        // 现在 updateGameTime 被移除了，因为我们不想在游戏暂停时更新时间
        if (!Pause) {
            updateMissileList();
            updateEnemyList();
            updateBackground();
            updateGameTime(); // 更新游戏时间，仅当游戏未暂停时
            gameModel.updateMissileList(missileList);
            gameModel.updateEnemyList(enemyList);
            gameView.updateGameModel(gameModel);
        }
    }

    private void updateGameTime() {
        if (!Pause) {
            // 当游戏未暂停时，更新游戏时间
            long totalElapsedTime = Utils.getCurrentTime() - gameModel.getStartTime();
            gameModel.setGameTime(formatGameTime(totalElapsedTime));
            gameView.updateGameTime();
        }
        // 注意：我们不再更新 gameModel 的 pauseTime 或 pauseStartTime
    }

    private String formatGameTime(long timeInSeconds) {
        // 将时间转换为分钟和秒
        long minutes = (timeInSeconds % 3600) / 60;
        long seconds = timeInSeconds % 60;
        // 返回格式化的时间字符串
        return String.format("%02d:%02d", minutes, seconds);
    }


    private void updateBackground() {
        int backgroundYNumber = gameModel.getBackgroundYNumber();
        backgroundYNumber = backgroundYNumber == 0 ? -1000 : backgroundYNumber+5;
        gameModel.setBackgroundYNumber(backgroundYNumber);
    }


    public GameController(GameModel gameModel, GameView gameView,CallBack callBack) {
        initController(gameModel,gameView);
        updatePlayerPlane();
        setCallBack(callBack);

        gameView.setTimer(new Timer(40, e -> {
            updateGameModel();
            checkPlayerEnmeyCollision();
            checkPlayerMissileCollision();
            checkEnmeyCollision();
            gameView.repaint();
        }));

        gameView.getScoreLabel().setText("积分：0");
        gameView.getTimeLabel().setText("游戏时长：00:00");
        gameView.getHpLabel().setText("HP：6");
    }

    private void checkPlayerMissileCollision() {
        int i = playerPlane.checkMissileCollision(gameModel.getMissileList());
        if (i > -1) {
            playerPlane.setHP(playerPlane.getHP());
            missileList.get(i).setHit(true);
            missileList.remove(i);
            gameModel.updatePlayerPlane(playerPlane);
            gameModel.updateMissileList(missileList);
            gameView.getHpLabel().setText("HP:"+playerPlane.getHP().toString());

            if (playerPlane.getHP() == 0) {
                if (callBack != null) {
                    callBack.onCallBack(score.toString());
                    playerPlane.setHP(6);
                    playerPlane.setX(200);
                    playerPlane.setY(700);
                    gameModel.updatePlayerPlane(playerPlane);
                    gameModel.updateMissileList(new ArrayList<>());
                } else {
                    System.out.println("null");
                }
            }
        }
    }

    private void checkEnmeyCollision() {
        for (int i = 0;i < enemyList.size();i++) {
            Enemy enemy = enemyList.get(i);
            List<Missile> missiles = gameModel.getMissileList();
            int mIndex = enemy.checkCollision(missiles);
            if (mIndex > -1) {
                enemyList.remove(i);
                try {
                    missiles.get(mIndex).setHit(true);
                    missiles.remove(mIndex);
                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("catch:");
//                    System.out.println("GameController::checkEnmeyCollision size="+missiles.size()+",mIndex=" + mIndex);
                }

                score+=10;
//                System.out.println("GameController::checkEnmeyCollision enemy="+enemy.createTime+","+Utils.getCurrentTimeFormat());
                gameModel.updateEnemyList(enemyList);
                gameModel.updateMissileList(missiles);
                gameView.getScoreLabel().setText("积分："+score);
                break;
            }
        }
    }

    private void checkPlayerEnmeyCollision() {
        int index = playerPlane.checkEnemyCollision(gameModel.getEnemyList());
        if (index > -1) {
            if (callBack != null) {
                callBack.onCallBack(score.toString());
                playerPlane.setHP(6);
                playerPlane.setX(200);
                playerPlane.setY(700);
                gameModel.updatePlayerPlane(playerPlane);
                gameModel.updateEnemyList(new ArrayList<>());
            } else {
                System.out.println("null");
            }
        }
    }

    private void initController(GameModel gameModel,GameView gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        this.gameView.setFocusable(true);
        this.score = 0;

        this.playerPlane = gameModel.getPlayerPlane();
        this.missileList = gameModel.getMissileList();
        this.enemyList = gameModel.getEnemyList();
        gameModel.setStartTime(Utils.getCurrentTime());
//        gameModel.setPauseTime(0);
        isPause(true);
    }

    public void rebirth() {
        gameView.getHpLabel().setText("HP：6");
    }
}
