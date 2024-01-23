package PlaneGame.Object;

import PlaneGame.Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerPlane extends GameObject{
    //玩家血条
    private int HP;

    public Integer getHP() {
        return HP;
    }

    public void setHP(int HP) {
//        System.out.println("setHP  HP="+HP+"  ->"+Utils.getCurrentTimeFormat());
        this.HP = HP;
    }

    /*
                负责人：小茂
                功能：
                创建玩家飞机对象的构造方法，调用父类构造，HP初始化为6。
                mageIO读入图片赋值给this.image。
                参数：
                - int x: 初始 x 坐标
                - int y: 初始 y 坐标
                - int width: 飞机宽度
                - int height: 飞机高度
                返回：void
            */
    public PlayerPlane(int x, int y, int width, int height) {
        super( x, y, width, height);
        //实现
        this.HP=6;
        try {
            this.image=ImageIO.read(new File("Game/src/PlaneGame/Object/playerplane.png"));  //飞机图片路径
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
       负责人：小茂
       功能：
       执行碰撞检测，检查玩家飞机与导弹、敌机的碰撞情况。如果发生碰撞，执行相应的处理逻辑。
       使用Rectangle类的intersects方法检测碰撞
       Rectangle playerBounds = getBounds();

       检测与导弹的碰撞
       for (Missile missile : missiles) {
           if (发生碰撞) {
               // 发生碰撞，可以执行相应的事件
           }
       }

       检测与敌机的碰撞
       for (Enemy enemy : enemies) {
           if (发生碰撞) {
               // 发生碰撞，可以执行相应的事件
           }
       }
       参数：
       - List<Missile> missiles: 导弹列表
       - List<Enemy> enemies: 敌机列表
       返回：int 检查后plane的HP，可以为负数
   */
    public int checkCollision(List<Missile> missiles, List<Enemy> enemies) {
        //实现
        //检测与导弹的碰撞
        Rectangle playerBounds = new Rectangle(super.x,super.y,super.width,super.height);
        for (Missile missile : missiles) {
            Rectangle missileBounds = new Rectangle(missile.x,missile.y,missile.width,missile.width); // 获取导弹边界矩形
            if (playerBounds.intersects(missileBounds) && !missile.isPlayerMissed()) { // 如果玩家飞机与导弹发生碰撞
                // 处理与导弹碰撞事件，减少玩家飞机HP
                handleMissileCollision(missile);
            }
        }

        // 检测与敌机的碰撞
        for (Enemy enemy : enemies) {
            Rectangle enemyBounds = new Rectangle(enemy.x,enemy.y,enemy.width,enemy.width); // 获取敌机边界矩形
            if (playerBounds.intersects(enemyBounds)) { // 如果玩家飞机与敌机发生碰撞
                // 处理与敌机碰撞事件，玩家血量降为0
                handleEnemyCollision(enemy);
            }
        }

        return HP;
    }

    public int checkMissileCollision(List<Missile> missiles) {
        Rectangle playerBounds = new Rectangle(super.x, super.y, super.width, super.height);

        for (int i = 0; i < missiles.size(); i++) {
            Missile missile = missiles.get(i);
            Rectangle missileBounds = new Rectangle(missile.x, missile.y, missile.width, missile.width);

            if (playerBounds.intersects(missileBounds) && !missile.isPlayerMissed() && !missile.isHit()) {
                // 处理与导弹碰撞事件，减少玩家飞机HP
//                System.out.println("checkMissileCollision()  "+missile);
                handleMissileCollision(missile);
                return i; // 返回导弹在列表中的下标
            }
        }

        return -1; // 没有碰撞发生
    }

    public int checkEnemyCollision(List<Enemy> enemies) {
        Rectangle playerBounds = new Rectangle(super.x, super.y, super.width, super.height);

        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            Rectangle enemyBounds = new Rectangle(enemy.x, enemy.y, enemy.width, enemy.width);

            if (playerBounds.intersects(enemyBounds)) {
                // 处理与敌机碰撞事件，玩家血量降为0
                handleEnemyCollision(enemy);
                return i; // 返回敌机在列表中的下标
            }
        }

        return -1; // 没有碰撞发生
    }



    /*
        负责人：小茂
        功能：
        处理玩家飞机与导弹碰撞的事件，减少生命值。
        参数：Missile
        返回：void
    */
    private void handleMissileCollision(Missile missile) {
        //实现
        // 处理与导弹碰撞事件，减少玩家飞机HP
        HP -= missile.getPower();
    }

    /*
        负责人：小茂
        功能：
        处理玩家飞机与敌机碰撞的事件，减少生命值。
        参数：Enemy
        返回：void
    */
    private void handleEnemyCollision(Enemy enemy) {
        HP = 0;
    }
}
