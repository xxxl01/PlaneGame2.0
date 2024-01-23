package PlaneGame.View;


import java.util.List;
import PlaneGame.Model.GameModel;
import PlaneGame.Object.Enemy;
import PlaneGame.Object.Missile;
import PlaneGame.PanelTest;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private GameModel gameModel;
    private Timer timer;
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel HpLabel;
    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        initView();
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }



    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JLabel getHpLabel() {
        return HpLabel;
    }

    //在这里初始布局
    private void initView() {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setOpaque(false);
        labelsPanel.setBackground(new Color(0,0,0,0));
        labelsPanel.setLayout(new GridLayout(1, 3, 10, 0));
        //添加一个面板存放三个属性

        int HP = 6;//测试用

        setSize(500,1000);

        setBackground(Color.WHITE);//设置暂时背景

        //创建三个标签
        scoreLabel = new JLabel();
        timeLabel = new JLabel();//设置getGameTime为00:00格式传入，添加更新time的方法
        HpLabel = new JLabel();



        //三个标签的属性
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        labelsPanel.add(scoreLabel);


        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        labelsPanel.add(timeLabel);


        HpLabel.setHorizontalAlignment(JLabel.CENTER);
        HpLabel.setFont(new Font("黑体", Font.PLAIN, 20));
        labelsPanel.add(HpLabel);

        // 设置白色背景图片
//        ImageIcon backgroundImage = new ImageIcon("D:\\Decument\\JavaProject\\PlaneGame\\Game\\src\\PlaneGame\\View\\background.png"); // 替换为图片路径
//        JLabel backgroundLabel = new JLabel(backgroundImage);
//        backgroundLabel.setLayout(new BorderLayout());
//        backgroundLabel.setOpaque(false);
//        backgroundLabel.add(labelsPanel, BorderLayout.NORTH);

        //将标签显示在窗口上方距为10像素的位置
        labelsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        add(labelsPanel, BorderLayout.NORTH);
    }

    //更新GameModel
    public void updateGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    /*
        负责人：昂威龙
        功能：
        设置定时器，将传入的 javax.swing.Timer 赋值给 this.timer，并调用 start 方法启动定时器。
        参数：
        - Timer timer: 待设置的定时器
        返回：void
    */
    public void setTimer(Timer timer) {
        //实现
        this.timer = timer;
        this.timer.start();
    }
    /*
        负责人：昂威龙
        功能：
        调用 g.drawImage 方法，根据 GameModel 中的玩家飞机信息绘制玩家飞机。鼠标焦点为图片的中心。
        参数：
        - Graphics g: 绘图上下文对象
        返回：void
    */
    public void drawPlayerPlane(Graphics g) {
        //实现
//        Image playerPlaneImage = gameModel.getPlayerPlane().getImage();  //获取玩家飞机图片
//        int x = getX() - playerPlaneImage.getWidth(null) / 2; //使飞机在光标焦点处生成
//        int y = getY() - playerPlaneImage.getHeight(null) / 2;
//        System.out.println(x + "," + y);
//        g.drawImage(playerPlaneImage,x,y,null);
        gameModel.getPlayerPlane().draw(g);
    }
    /*
        负责人：昂威龙
        功能：
        调用 g.drawImage 方法，根据 GameModel 中的导弹List信息绘制每一个导弹。
        参数：
        - Graphics g: 绘图上下文对象
        返回：void
    */
    public void drawMissile(Graphics g) {
        //实现
        List<Missile> missileList = gameModel.getMissileList();
        for(Missile missile : missileList){
            missile.draw(g);
        }
    }
    /*
        负责人：昂威龙
        功能：
        调用 g.drawImage 方法，根据 GameModel 中的敌机信息绘制每一个敌机。
        参数：
        - Graphics g: 绘图上下文对象
        返回：void
    */
    public void drawEnemy(Graphics g) {
        //实现
        List<Enemy> enemyList = gameModel.getEnemyList();
//        System.out.println("enemyList.size()"+enemyList.size());
        for(Enemy enemy : enemyList){
            enemy.draw(g);
        }
    }
    /*
        负责人：昂威龙
        功能：
        重写绘画函数。先调用父类 paintComponent，再执行绘制玩家、导弹、敌人的逻辑。
        参数：
        - Graphics g: 绘图上下文对象
        返回：void
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //实现
        drawBackground(g);
        drawPlayerPlane(g);
        drawMissile(g);
        drawEnemy(g);
    }

    private void drawBackground(Graphics g) {
        g.drawImage(
                gameModel.getBackground(),
                0,
                gameModel.getBackgroundYNumber(),
                500,
                2000,
                null);
    }

    public static void main(String[] args) {
        PanelTest.test(new GameView(GameModel.getInitModel()));
    }

    public void updateGameTime() {
        String gameTime = gameModel.getGameTime();
        timeLabel.setText("游戏时间:"+gameTime);
    }
}
