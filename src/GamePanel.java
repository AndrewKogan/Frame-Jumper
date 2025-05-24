import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{


    MouseInputs mouseH = new MouseInputs();
    KeyInputs keyH = new KeyInputs();

    Player player;
    ArrayList<Wall> walls = new ArrayList<>();

    public static int FPS = 60;

    Thread gameThread;

    public GamePanel() {
        player = new Player(400,300,this);
        makeWalls();
        this.setPreferredSize(new Dimension(700, 700));
        this.setBackground(Color.LIGHT_GRAY);
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
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        for(int i = 0; i < walls.size(); i++){
            walls.get(i).draw(g2);
        }
    }

    public void makeWalls(){
        for(int i = 50; i < 650; i+=50){
            walls.add(new Wall(i,600,50,50));
        }
        walls.add(new Wall(50,550,50,50));
        walls.add(new Wall(50,500,50,50));
        walls.add(new Wall(50,450,50,50));
        walls.add(new Wall(600,550,50,50));
        walls.add(new Wall(600,500,50,50));
        walls.add(new Wall(600,450,50,50));
        walls.add(new Wall(450,550,50,50));
    }
}