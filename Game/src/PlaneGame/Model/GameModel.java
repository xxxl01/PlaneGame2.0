package PlaneGame.Model;

import PlaneGame.Object.Enemy;
import PlaneGame.Object.Missile;
import PlaneGame.Object.PlayerPlane;
import PlaneGame.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    功能：游戏模型类，存储游戏中的玩家飞机、敌人列表和导弹列表，并提供相应的更新和获取方法
    参数：
        - PlayerPlane playerPlane: 玩家飞机对象
        - List<Enemy> enemyList: 敌人对象列表
        - List<Missile> missileList: 导弹对象列表
*/
public class GameModel {
    private PlayerPlane playerPlane;
    private List<Enemy> enemyList;
    private List<Missile> missileList;

    private int score;
    private String gameTime;
    private int backgroundYNumber;
    private Image background;
    private long startTime;
    private long pauseStartTime;
    private long pauseTime;

    public long getPauseTime() {
        return pauseTime;
    }
    private long gameElapsedTime; // 记录游戏已经过去的时间

    public long getGameElapsedTime() {
        return gameElapsedTime;
    }

    public void setGameElapsedTime(long gameElapsedTime) {
        this.gameElapsedTime = gameElapsedTime;
    }
    public void setPauseTime(long pauseTime) {
        this.pauseTime = pauseTime;
    }

    public long getPauseStartTime() {
        return pauseStartTime;
    }

    public void setPauseStartTime(long pauseStartTime) {
        this.pauseStartTime = pauseStartTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public int getBackgroundYNumber() {
        return backgroundYNumber;
    }

    public void setBackgroundYNumber(int backgroundYNumber) {
        this.backgroundYNumber = backgroundYNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    /*
            构造方法，用于初始化游戏模型对象
            参数：
                - PlayerPlane playerPlane: 玩家飞机对象
                - List<Enemy> enemyList: 敌人对象列表
                - List<Missile> missileList: 导弹对象列表
                GameView调用
            返回：无
        */
    public GameModel(PlayerPlane playerPlane, List<Enemy> enemyList, List<Missile> missileList) {
        this.playerPlane = playerPlane;
        this.enemyList = enemyList;
        this.missileList = missileList;
        this.score = 0;
        this.gameTime = "00:00";
        this.backgroundYNumber = -1000;
        this.pauseStartTime = 0;
        this.pauseTime = 0;
        this.startTime = 0;
        try {
            this.background = ImageIO.read(new File("Game\\src\\PlaneGame\\View\\background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        更新玩家飞机对象的方法
        参数：
            - PlayerPlane playerPlane: 新的玩家飞机对象
            GameController调用
        返回：无
    */
    public void updatePlayerPlane(PlayerPlane playerPlane) {
        this.playerPlane = playerPlane;
    }

    /*
        更新敌人对象列表的方法
        参数：
            - List<Enemy> enemyList: 新的敌人对象列表
            GameController调用
        返回：无
    */
    public void updateEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }

    /*
        更新导弹对象列表的方法
        参数：
            - List<Missile> missileList: 新的导弹对象列表
            GameController调用
        返回：无
    */
    public void updateMissileList(List<Missile> missileList) {
        this.missileList = missileList;
    }

    /*
        获取当前玩家飞机对象的方法
        参数：无
        返回：
            - PlayerPlane: 当前玩家飞机对象
            GameController，GameView调用
    */
    public PlayerPlane getPlayerPlane() {
        return playerPlane;
    }

    /*
        获取当前敌人对象列表的方法
        参数：无
        返回：
            - List<Enemy>: 当前敌人对象列表
            GameController，GameView调用
    */
    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    /*
        获取当前导弹对象列表的方法
        参数：无
        返回：
            - List<Missile>: 当前导弹对象列表
            GameController，GameView调用
    */
    public List<Missile> getMissileList() {
        return missileList;
    }

    public static GameModel getInitModel() {
        return new GameModel(new PlayerPlane(200,700,50,50),new ArrayList<>(),new ArrayList<>());
    };

    public void addPauseTime(int add) {
        pauseTime+=add;
    }
}
