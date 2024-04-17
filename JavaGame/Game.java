package JavaGame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends JPanel implements ActionListener, KeyListener {

    JFrame frame;

    public ArrayList<GameObjects> AllGameObjects = new ArrayList<GameObjects>();
    public ArrayList<GameObjects> AllObjectsToRender = new ArrayList<GameObjects>();

    // Image Vars
    public Image RomanSoldier;
    public Image RomanChariot;
    public Image Grass;

    public int WorldXpos = 0;
    public int WorldYpos = 0;

    int ScreenWidth = 0;
    int ScreenHeight = 0;

    int GameSize = 50;
    int NumberOfHorse = 50;

    JLabel TimerText;
    int TimeCallAmount = 0;
    float TimeInSeconds = 0;

    Game(JFrame MainFrame) {
        frame = MainFrame;
        frame.addKeyListener(this);

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

    TextField KeyTextaa;

    void Start() {
        for (int i = 0; i < NumberOfHorse; i++) {
            Horse obja = new Horse("Horse" + i, 5, 2, 0, GameSize * i);
            obja.Xpos += i;
            System.err.println(((GameObjects) obja).Xpos);
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    Boolean Left = false, Right = false, Up = false, Down = false;

    public void keyPressed(KeyEvent e) {

        System.err.println(Left + " " + Right + " " + Up + " " + Down);

        if (e.getKeyCode() == KeyEvent.VK_A) {
            Left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            Right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            Down = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            Right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            Down = false;
        }
    }

    void Update() {

        if (Up) {
            WorldYpos += 5;
        }

        if (Down) {
            WorldYpos -= 5;
        }
        if (Right) {
            WorldXpos -= 5;
        }
        if (Left) {
            WorldXpos += 5;
        }

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

        int NumOfGrassObjs = 0;
        for (int i = 0; i < (ScreenWidth - WorldXpos); i += Grass.getWidth(TimerText)) {
            if (i >= -WorldXpos - Grass.getWidth(TimerText)) {
                int h = Grass.getHeight(TimerText);
                for (int y = 0; y < ScreenHeight - WorldYpos + h; y += h) {
                    g.drawImage(Grass, i + WorldXpos, y + WorldYpos, null);
                    NumOfGrassObjs++;
                }
            }
        }

        for (int a = 0; a < NumberOfHorse + 1; a++) {
            g.drawLine(0, (GameSize * a) + WorldYpos, ScreenWidth, (GameSize * a) + WorldYpos);
        }

        for (int i = 0; i < AllGameObjects.size(); i++) {
            Horse TempHorse = (Horse) AllGameObjects.get(i);
            g.drawImage(RomanChariot, TempHorse.Xpos + WorldXpos, (GameSize * i) + WorldYpos, null);
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
                this.Speed = Math.clamp(Speed, 3, 5f);
            }
        }
    }

}
