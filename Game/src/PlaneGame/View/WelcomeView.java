package PlaneGame.View;

import PlaneGame.PanelTest;
import PlaneGame.Utils;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;


public class WelcomeView extends JPanel {
    //设为按钮组件为常量
    private final JButton startButton;
    private final JButton exitButton;
    private final JButton rankButton;
    private final JLabel titleLabel;

    /*
    功能：
    负责人：刘华伟
    创建欢迎界面的构造方法，用于初始化欢迎界面的组件等。不设置监听器，在后面设置，设计好布局。
    参数：无
    返回：void
    */
    public WelcomeView() {
        // 初始化组件
        startButton = new JButton("开始游戏 ");
        exitButton = new JButton("退出游戏");
        rankButton = new JButton("排行榜");
        titleLabel = new JLabel("飞机大战", JLabel.CENTER);
        //按钮透明化
        startButton.setContentAreaFilled(false);
        exitButton.setContentAreaFilled(false);
        rankButton.setContentAreaFilled(false);
        //设置按钮大小
        startButton.setPreferredSize(new Dimension(30,30));
        exitButton.setPreferredSize(new Dimension(30,30));
        rankButton.setPreferredSize(new Dimension(30,30));
        //消除边框
        startButton.setBorderPainted(false);
        rankButton.setBorderPainted(false);
        exitButton .setBorderPainted(false);
        // 设置布局
        setLayout(new GridLayout(4, 2)); // 垂直布局
        //设置label大小
        Font font =new Font("Serif",Font.BOLD,40);
        titleLabel.setFont(font);

        // 将组件添加到面板
        add(titleLabel);
        add(startButton);
        add(rankButton);
        add(exitButton);

    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getRankButton() {
        return rankButton;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        PanelTest.test(new WelcomeView());
    }

}
