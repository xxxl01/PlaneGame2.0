package PlaneGame.View;

import PlaneGame.PanelTest;

import javax.swing.*;
import java.awt.*;

public class PauseView extends JPanel {
    //设为按钮组件为常量
    private final JButton continueButton;
    private final JButton mainMenuButton;
    private final JLabel pauseLabel;

    /*
    功能：
    负责人：刘华伟
    创建暂停界面的构造方法，用于初始化暂停界面的组件等。不设置监听器，在后面设置，设计好布局。
    参数：无
    返回：void
    */
    public PauseView() {
        // 初始化组件
        pauseLabel = new JLabel("暂停",JLabel.CENTER);
        continueButton = new JButton("继续");
        mainMenuButton = new JButton("主菜单");

        //按钮透明化
        continueButton.setContentAreaFilled(false);
        mainMenuButton.setContentAreaFilled(false);
        //设置按钮大小
        continueButton.setPreferredSize(new Dimension(30,30));
        mainMenuButton.setPreferredSize(new Dimension(30,30));
        //消除边框
        continueButton.setBorderPainted(false);
        mainMenuButton.setBorderPainted(false);
        // 设置布局
        setLayout(new GridLayout(3, 1)); // 垂直布局

        //设置label字体大小
        Font font =new Font("Serif",Font.BOLD,30);
        pauseLabel.setFont(font);
        // 将按钮添加到面板
        add(pauseLabel);
        add(continueButton);
        add(mainMenuButton);
    }

    public JLabel getPauseLabel() {
        return pauseLabel;
    }

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    public static void main(String[] args) {
        PanelTest.test(new PauseView());
    }
}
