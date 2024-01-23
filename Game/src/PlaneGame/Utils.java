package PlaneGame;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Utils {
    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static long getCurrentTime() {
        long l = System.currentTimeMillis();
        return l/1000;
    }

    public static String getCurrentTimeFormat() {
        return new SimpleDateFormat("mm:ss.SSS").format(new Date());
    }
}
