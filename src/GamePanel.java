import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 48; // 48x48 tile
    final int scale = 1;
    public final int tileSize = originalTileSize * scale; // 48*48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    MouseInputs mouseH = new MouseInputs();
    KeyInputs keyH = new KeyInputs();

    int FPS = 60;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000./FPS;
        double delta  = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){ // updates info and draws screen
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
//        player.draw(g2);

        g2.dispose();
    }
}