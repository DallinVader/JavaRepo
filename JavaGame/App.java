package JavaGame;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame {
    App(int ax) {

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Rome Game");
        frame.getContentPane().setBackground(new Color(38, 139, 7));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(1600, 900);
        frame.setLocationRelativeTo(null);

        Game game = new Game(frame);
        frame.add(game);
    }
}
