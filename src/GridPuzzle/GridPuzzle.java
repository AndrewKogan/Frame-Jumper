package GridPuzzle;

import javax.swing.*;
import java.awt.*;

public class GridPuzzle{
    private JFrame[] windows = new JFrame[16];
    private Color[] colors = new Color[16];

    private JFrame submitWindow;

    public GridPuzzle() {
        colors[0] = Color.BLUE;
        colors[1] = Color.CYAN;
        colors[2] = Color.WHITE;
        colors[3] = Color.BLACK;
        colors[4] = Color.YELLOW;
        colors[5] = Color.RED;
        colors[6] = Color.ORANGE;
        colors[7] = Color.MAGENTA;
        colors[8] = Color.GREEN;
        colors[9] = Color.DARK_GRAY;
        colors[10] = Color.PINK;
        colors[11] = new Color(43, 74, 42);
        colors[12] = new Color(23, 58, 59);
        colors[13] = new Color(6, 9, 33);
        colors[14] = new Color(175, 136, 219);
        colors[15] = new Color(82, 2, 2);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        for (int i = 0; i < 16; i++) {
            JFrame window = new JFrame();

            window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("");

            window.add(new GridPanel(colors[i]));
            window.setSize(200, 200);
            Point spawnPos = new Point((int)(Math.random() * screenSize.width), (int)(Math.random() * screenSize.height));
            spawnPos = new Point(Math.clamp(spawnPos.x, 100, screenSize.width - 100), Math.clamp(spawnPos.y, 100, screenSize.height - 100));
            window.setLocation(spawnPos);

            window.setEnabled(true);
            window.setVisible(true);

            windows[i] = window;
        }

        submitWindow = new JFrame();
        submitWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        submitWindow.setResizable(false);
        submitWindow.setTitle("");

        submitWindow.add(new SubmitPanel(this));
        submitWindow.setSize(200, 65);
        submitWindow.setLocation(3 * screenSize.width / 4, 3  *screenSize.height / 4);

        submitWindow.setEnabled(true);
        submitWindow.setVisible(true);
    }

    public boolean checkPuzzleSolution() {
        Point checkPoint = windows[0].getLocationOnScreen();

        for (int i = 4; i < windows.length; i += 4) {
            for (int j = i - 3; j < i; j++) {
                if (windows[j].getLocationOnScreen().x <= checkPoint.x)
                    return false;
                checkPoint = windows[j].getLocationOnScreen();
            }

            if (windows[i].getLocationOnScreen().y <= checkPoint.y)
                return false;
            checkPoint = windows[i].getLocationOnScreen();
        }

        return true;
    }

    public void onPuzzleSolved() {
        System.out.println("Solved");
        for (JFrame window : windows)
            window.dispose();
        submitWindow.dispose();
    }

    public void quitPuzzle() {
        for (JFrame window : windows)
            window.dispose();
        submitWindow.dispose();
    }
}
