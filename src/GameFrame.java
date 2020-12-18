import java.awt.*;
import javax.swing.*;

class GameFrame extends JFrame {

    private GamePanel panel;

    GameFrame(Boolean bot) {
        panel = new GamePanel(bot);
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }



}
