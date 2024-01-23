package PlaneGame.Controller;
import PlaneGame.CallBack;
import PlaneGame.Model.GameModel;
import PlaneGame.Object.PlayerPlane;
import PlaneGame.Utils;
import PlaneGame.View.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.*;


public class GameFrame extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private WelcomeView welcomeView;
    private PauseView pauseView;
    private FailView failView;
    private GameView gameView;
    private GameController gameController;

    public GameFrame() {
        initializeViews();
        setLinteners();
        setupCardLayout();
        setupMainFrame();
    }

    /*
        负责人：宋吉
        功能：
        初始化各个 View。
            welcomeView = new WelcomeView();
            pauseView = new PauseView();
            failView = new FailView();
        调用setLinteners()
        参数：无
        返回：void
    */
    private void initializeViews() {
        cardLayout=new CardLayout();
        cardPanel=new JPanel();
        welcomeView = new WelcomeView();
        pauseView = new PauseView();
        failView = new FailView();

        GameModel gameModel = GameModel.getInitModel();
        gameView=new GameView(gameModel);
        gameController = new GameController(gameModel, gameView, params -> {
            gameController.isPause(true);
            failView.setScore(params[0]);
            cardLayout.show(cardPanel,"failView");
        });
    }

    /*
        负责人：宋吉
        功能：
        为各个 View 设置监听器，实现视图之间的跳转。
        welcomeView.getStartButton().addActionListener(e -> 逻辑))
        参数：无
        返回：void
    */
    private void setLinteners() {
        //点击开始游戏按钮--跳转游戏界面(先关闭欢迎界面，再打开游戏界面)
        welcomeView.getStartButton().addActionListener(e -> {
            GameModel gameModel = GameModel.getInitModel();
            gameView.updateGameModel(gameModel);
            gameController = new GameController(gameModel,gameView, params -> {
                gameController.isPause(true);
                failView.setScore(params[0]);
                cardLayout.show(cardPanel,"failView");
            });

            cardLayout.show(cardPanel,"gameView");
            gameController.isPause(false);
            gameView.requestFocus();
        });
        //点击关闭游戏按钮--直接结束进程
        welcomeView.getExitButton().addActionListener(e -> System.exit(0));
        //点击积分榜按钮--跳转积分榜页面
        welcomeView.getRankButton().addActionListener(e -> {});
        //游戏中暂停界面，点击继续游戏--关闭暂停界面，将控制界面暂停属性设置为false
        pauseView.getContinueButton().addActionListener(e->{
            cardLayout.show(cardPanel,"gameView");
            gameView.requestFocus();
            gameController.isPause(false);
        });
        //游戏中暂停界面，点击返回主菜单--关闭暂停界面，跳转主菜单界面
        pauseView.getMainMenuButton().addActionListener(e-> cardLayout.show(cardPanel,"welcomeView"));
        //失败界面点击复活--关闭失败界面，继续游戏
        failView.getRebirthButton().addActionListener(e->{
            gameController.rebirth();
            cardLayout.show(cardPanel,"gameView");
            gameView.requestFocus();
            gameController.isPause(false);
        });
        //失败界面点击返回主菜单按钮--关闭失败节目，打开主页面
        failView.getMainMenuButton().addActionListener(e->{
            cardLayout.show(cardPanel,"welcomeView");
        });
        /*gameView.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按下键盘");
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode()==27){
                    System.out.println("打开暂停界面");
                    cardLayout.show(cardPanel,"pauseView");
                }
            }
        });*/
        gameView.setFocusable(true);
        gameView.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                cardLayout.show(cardPanel,"pauseView");
                gameController.isPause(true);
            }
        });
    }

    /*
         负责人：宋吉
         功能：
         设置卡片布局，将各个 View 添加到卡片布局中，并在 cardLayout 上显示 welcomeView。
         参数：无
         返回：void
     */
    private void setupCardLayout() {
        //实现
        cardPanel.setLayout(cardLayout);
        cardPanel.add(welcomeView,"welcomeView");
        cardPanel.add(pauseView,"pauseView");
        cardPanel.add(failView,"failView");
        cardPanel.add(gameView,"gameView");
        cardLayout.show(cardPanel,"welcomeView");
    }

    /*
        负责人：宋吉
        功能：
        设置主窗口（this）的基本属性，如大小、关闭操作等。
        大小为500X1000。默认关闭操作EXIT_ON_CLOSE。
        参数：无
        返回：void
    */
    private void setupMainFrame() {
        //实现
        this.setSize(500, 1000); // 设置窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭操作
        this.setLayout(new BorderLayout());
        this.add(cardPanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Utils.setLookAndFeel();
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}

