package JavaGame;

import JavaGame.GameObjects;
import JavaGame.GameObjects.Horse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Game extends JPanel implements ActionListener {

    JFrame frame;

    // Image Vars
    public Image RomanSoldier;
    public Image RomanChariot;
    public Image Grass;

    int ScreenWidth = 0;
    int ScreenHeight = 0;

    int GameSize = 50;
    int NumberOfHorse = 7;

    JLabel TimerText;
    int TimeCallAmount = 0;
    float TimeInSeconds = 0;

    Game(JFrame MainFrame) {
        frame = MainFrame;

        ScreenWidth = frame.getWidth();
        ScreenHeight = frame.getHeight();

        System.err.println("At least this worked " + ScreenWidth + " " + ScreenHeight);
        Timer timer = new Timer(16, this);
        timer.start();

        Start();

        JButton button = new JButton("Start Race");
        button.addActionListener(this);
        add(button);
        button.setBounds(0, 0, ScreenHeight / 50, ScreenWidth / 2);

        TimerText = new JLabel(String.valueOf(TimeInSeconds));
        add(TimerText);
    }

    void Start() {
        for (int i = 0; i < 5; i++) {
            Horse obja = new Horse(50);
            ((GameObjects) obja).Xpos += i;
            System.err.println(((GameObjects) obja).Xpos);
        }
    }

    void Update() {
        TimeInSeconds = TimeCallAmount / 31.25f;
        TimeCallAmount++;

        TimerText.setText(String.valueOf(Math.round(TimeInSeconds)));
    }

    public void paintComponent(Graphics g) {
        ScreenWidth = frame.getWidth();

        super.paintComponent(g);
        Draw(g);
    }

    public void Draw(Graphics g) {
        RomanChariot = new ImageIcon(getClass().getResource("Images/RomanChariot.png")).getImage();
        for (int a = 0; a < NumberOfHorse + 1; a++) {
            g.drawLine(0, GameSize * a, ScreenWidth, GameSize * a);
            g.drawImage(RomanChariot, 0 + RomanChariot.getWidth(TimerText) + (int) TimeInSeconds, GameSize * a, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        Update();
    }
}
