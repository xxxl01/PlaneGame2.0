package PlaneGame;

import PlaneGame.View.FailView;

import javax.swing.*;
import java.awt.*;

public class PanelTest {
    public static void test(JPanel jPanel) {
        // 示例用法，创建FailView实例并显示
        JFrame frame = new JFrame("Test");
        frame.setSize(500,1000);
        CardLayout cardLayout = new CardLayout();
        frame.setLayout(cardLayout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel parentPanel = new JPanel();
        parentPanel.setLayout(cardLayout);
        parentPanel.add(jPanel,"l");
        cardLayout.show(parentPanel,"l");
//        frame.getContentPane().add(failView);
//        frame.add(new JPanel(){{setSize(500,1000);}});
        frame.add(parentPanel);

//        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
