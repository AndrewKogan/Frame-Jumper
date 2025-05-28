import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Frame 1");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(new Point(point.x - 700, point.y - 350));
        window.setSize(new Dimension(700, 740));
        window.setVisible(true);

        gamePanel.startGameThread();

        JFrame window2 = new JFrame();
        window2.setTitle("Frame 2");

        window2.pack();
        window2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window2.setLocation(window.getX() + 685, window.getY());
        window2.setSize(new Dimension(700, 740));
        window2.setVisible(true);
        window.setVisible(true);
    }
}