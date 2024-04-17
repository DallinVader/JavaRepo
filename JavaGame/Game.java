package JavaGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Game extends JPanel implements ActionListener {

    JFrame frame;

    public ArrayList<GameObjects> AllGameObjects = new ArrayList<GameObjects>();
    public ArrayList<GameObjects> AllObjectsToRender = new ArrayList<GameObjects>();

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
        Timer timer = new Timer(8, this);
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
        for (int i = 0; i < 7; i++) {
            Horse obja = new Horse("Horse" + i, 5, 2, 0, GameSize * i);
            obja.Xpos += i;
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
        Grass = new ImageIcon(getClass().getResource("Images/Grass.png")).getImage();

        for (int i = 0; i < ScreenWidth; i += Grass.getWidth(TimerText)) {
            for (int y = 0; y < ScreenHeight; y += Grass.getHeight(TimerText)) {
                g.drawImage(Grass, i, y, null);
            }
            System.err.println(i / Grass.getWidth(TimerText));
        }

        for (int a = 0; a < NumberOfHorse + 1; a++) {
            g.drawLine(0, GameSize * a, ScreenWidth, GameSize * a);
        }

        for (int i = 0; i < AllGameObjects.size(); i++) {
            Horse TempHorse = (Horse) AllGameObjects.get(i);
            g.drawImage(RomanChariot, TempHorse.Xpos, GameSize * i, null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        Update();
    }

    public class GameObjects {
        String Name;
        int Xpos = 0;
        int Ypos = 0;
        Image Image = null;
        Boolean RenderObj = false;

        GameObjects(Image ImageToRender, int Xpos, int Ypos) {
            this.Name = "GameObject";
            this.Xpos = Xpos;
            this.Ypos = Ypos;
            this.Image = ImageToRender;

            AllGameObjects.add(this);
            if (this.Image != null) {
                AllObjectsToRender.add(this);
            }
        }

    }

    public class Horse extends GameObjects {
        float Speed = 1;
        float SpeedAdjustments;
        float time = 0;

        public Horse(String Name, int Speed, float SpeedAdjustments, int Xpos, int Ypos) {
            super(RomanChariot, Xpos, Ypos);
            this.SpeedAdjustments = SpeedAdjustments;
            this.Speed = Speed;
            this.Name = Name;

            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(() -> Update(), 0, 16, TimeUnit.MILLISECONDS);

        }

        void Update() {
            AdjustSpeed();
            this.Xpos += this.Speed;
        }

        float CoolDown = 0;

        void AdjustSpeed() {
            if (CoolDown <= TimeInSeconds) {
                CoolDown += 0.75f;
                this.Speed += Math.random() * this.SpeedAdjustments - (this.SpeedAdjustments / 2);
                this.Speed = Math.clamp(Speed, 2, 5f);
            }
        }
    }

}
