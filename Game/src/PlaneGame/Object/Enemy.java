package PlaneGame.Object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Enemy extends GameObject{
    private int power;

    public int getPower() {
        return power;
    }

    /*
            负责人：许凯锐
            功能：
            创建玩家敌人对象的构造方法，调用父类构造，power初始化为6。
            Gamemodel调用
            参数：
            - int x: 初始 x 坐标
            - int y: 初始 y 坐标
            - int width: 敌人宽度
            - int height: 敌人高度
        */
    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.power = 6;
        try {
            this.image = ImageIO.read(new File("Game/src/PlaneGame/Object/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        负责人：许凯锐
        功能：
        用于更新敌人对象的位置。move 方法使敌人向下移动，增加其 y 坐标值。x坐标随机加减，但不能超出屏幕范围。
        GameController调用
        参数:void
        返回:void
    */
    public void move() {
        // 向下移动
        y += 5;

        // 随机移动 x 坐标，但不能超出屏幕范围
        int newX = x + (int)(Math.random() * 11) - 5; // 随机生成 -5 到 5 之间的偏移量
        if (newX < 0) {
            newX = 0; // 如果超出左边界，设为0
        } else if (newX + width > 500) {
            newX = 500 - width; // 如果超出右边界，调整为屏幕宽度减去敌人宽度
        }
        x = newX;
    }


    /*
        负责人：许凯锐
        功能：
        执行碰撞检测，检查敌机与导弹的碰撞情况。使用 Rectangle 类的 intersects 方法检测碰撞
        GameController调用
        参数：
        - Missile missile: 待检测的导弹对象
        返回：boolean，表示是否发生碰撞
        12/29修改，改变参数为List<Missile>,返回值设置为int，返回与敌机碰撞的Missile在List是第几个，从0开始，没有则返回-1
    */
    public int checkCollision(List<Missile> missiles) {
        Rectangle enemyRect = new Rectangle(x, y, width, height); // 创建敌机的矩形边界

        for (int i = 0; i < missiles.size(); i++) {
            Missile missile = missiles.get(i);
            Rectangle missileRect = new Rectangle(missile.getX(), missile.getY(), missile.getWidth(), missile.getHeight()); // 创建导弹的矩形边界
            if (enemyRect.intersects(missileRect) && missile.isPlayerMissed() && !missile.isHit()) {
//                System.out.println("Enemy::checkCollision size="+missiles.size()+",i=" + i);
                return i; // 如果敌机的矩形边界与导弹的矩形边界相交，返回导弹在List中的索引
            }
        }

        return -1; // 没有发生碰撞，返回-1
    }


}
