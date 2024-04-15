package JavaGame;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class JavaExeption {

    public static ArrayList<GameObject> AllObjectsInExestance = new ArrayList<>();

    public static class MyFrame extends JFrame {
        MyFrame() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(1600, 900);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setTitle("Does this do what I think it does?");
        }

        public void paint(Graphics g) {
            Graphics2D G2D = (Graphics2D) g;

            G2D.drawLine(0, 0, this.getSize().width, this.getSize().height);
            // G2D.drawImage(this.Img, 5, 5, null);
        }
    }

    public static class GameObject {
        String Name;
        Image img;
        float XPos;
        float YPos;

        GameObject(String Name, float X, float Y, Image img) {
            this.Name = Name;
            this.XPos = X;
            this.YPos = Y;
            this.img = img;

            AllObjectsInExestance.add(this);
            System.err.println(AllObjectsInExestance.getLast().Name);
        }
    }

    public static void main(String[] args) throws IOException {
        URL imPath = MyFrame.class.getResource("Images/Roman.png");
        Image im = ImageIO.read(imPath);

        MyFrame frame = new MyFrame();

        Scanner scan = new Scanner(System.in);

        frame.setTitle(scan.nextLine());
        frame.paint(null);
    }
}