package PlaneGame.Object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Missile extends GameObject{
    //private int power：表示导弹的威力或攻击力，构造函数里，power 被初始化为 1。
    private int power;

    //private boolean isPlayerMissed：表示导弹是否由玩家发射。
    private boolean isPlayerMissed;

    private boolean hit;

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public int getPower() {
        return power;
    }

    public boolean isPlayerMissed() {
        return isPlayerMissed;
    }

    /*
                负责人：陈俱进
                功能：
                创建导弹对象的构造方法，调用父类构造，power初始化为1。
                mageIO读入图片赋值给this.image。根据是否为玩家的导弹赋不同值。
                玩家的导弹为当前路径下的“missile.png”
                敌机“enemy.png”
                参数：
                - int x: 初始 x 坐标
                - int y: 初始 y 坐标
                - int width: 导弹宽度
                - int height: 导弹高度
                12/29 修改，加入一个参数用于判断是否为玩家的导弹
            */
    public Missile(int x, int y, int width, int height,boolean isPlayerMissed) {
        super(x, y, width, height);
        this.power = 1;
        this.hit = false;
        this.isPlayerMissed = isPlayerMissed;
        //实现
        String path = isPlayerMissed ? "Game/src/PlaneGame/Object/missile.png" : "Game/src/PlaneGame/Object/emissile.png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        负责人：陈俱进
        功能：导弹的移动方法，用于更新导弹的位置。判断是否为玩家导弹，Y轴相应增加或减少。步长为90
        gamecontroller调用
        参数：void
        返回：void
        12/29 修改功能描述
    */
    public void move() {
        if(isPlayerMissed){
            y -= 30;
        }else{
            y += 30;
        }
    }
}
