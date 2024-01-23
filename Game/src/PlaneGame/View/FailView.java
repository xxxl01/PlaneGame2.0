package PlaneGame.View;

import PlaneGame.PanelTest;

import javax.swing.*;
import java.awt.*;

public class FailView extends JPanel {
    //设为按钮组件为常量
    private final JLabel scoreLabel;
    private final JLabel failLabel;
    private final JButton rebirthButton;
    private final JButton mainMenuButton;

    public void setScore(String score) {
        scoreLabel.setText("本局积分："+score);
    }

    /*
    功能：
    负责人：刘华伟
    创建失败界面的构造方法，用于初始化失败界面的组件等。不设置监听器，在后面设置，设计好布局。
    参数：无
    返回：void
    */
    public FailView() {
        // 初始化组件
        failLabel = new JLabel("游戏失败",JLabel.CENTER);
        rebirthButton = new JButton("复活");
        mainMenuButton = new JButton("返回主菜单");
        scoreLabel = new JLabel("本局积分：",JLabel.CENTER);

        // 设置布局
        setLayout(new GridLayout(6, 4)); // 垂直布局

        //按钮透明化
        rebirthButton.setContentAreaFilled(false);
        mainMenuButton.setContentAreaFilled(false);
        //设置按钮大小
        rebirthButton.setPreferredSize(new Dimension(30,30));
        mainMenuButton.setPreferredSize(new Dimension(30,30));
        //消除边框
        rebirthButton.setBorderPainted(false);
        mainMenuButton.setBorderPainted(false);
//        设置label字体大小
        Font font =new Font("Serif",Font.BOLD,30);
        failLabel.setFont(font);

        // 将组件添加到面板
        add(failLabel);
        add(scoreLabel);
        add(rebirthButton);
        add(mainMenuButton);
    }

    public  JLabel getFailLabel(){return  failLabel;};
    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JButton getRebirthButton() {
        return rebirthButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    public static void main(String[] args) {
        PanelTest.test(new FailView());
    }
}
