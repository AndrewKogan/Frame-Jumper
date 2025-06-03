import javax.swing.*;

public class GameManager {
    private static int level = 0;
    private static GamePanel[] levels;
    private static JFrame window;

    public static void startGame() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Frame Jumper");

        levels = new GamePanel[] {new LevelOne(), new LevelOne()};

        GamePanel gamePanel = levels[0];
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }

    public static void nextLevel() {
        window.dispose();

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Frame Jumper");
        level++;
        GamePanel gamePanel = levels[level];
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
