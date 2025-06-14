import javax.swing.*;

public class GameManager {
    public static int level = 0;
    private static GamePanel[] levels;
    private static JFrame window;

    public static void startGame() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Saving Matthias");

        levels = new GamePanel[] {
                new LevelOne(),
                new LevelTwo(),
                new LevelThree(),
                new LevelFour()
        };

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
        window.setResizable(false);
        window.setTitle("Saving Matthias");
        level++;
        GamePanel gamePanel = levels[level];
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
