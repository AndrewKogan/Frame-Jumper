import CodePuzzle.CodePuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, ActionListener {
    MouseInputs mouseH = new MouseInputs();
    KeyInputs keyH = new KeyInputs();

    Player player;
    Enemy enemy;
    ArrayList<Wall> walls = new ArrayList<>();
    ArrayList<Spikes> spikes = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();
    Door door;

    public final static int FPS = 60;

    private Thread gameThread;

    private Panel panelToDisplay = Panel.LEVEL;

    private Cutscene testCutscene;
    JButton cutsceneContinueButton;

    private Interactable testInteractable;

    LevelEndInteractable levelEnd;
    CodePuzzleInteractable codePuzzleStart;
    GridPuzzleInteractable gridPuzzleStart;

    public GamePanel() {
        player = new Player(350,300,this);
        makeWalls();
        makeSpikes();
        makeEnemies();
        this.setPreferredSize(new Dimension(700, 700));
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);

        testCutscene = new Cutscene("https://www.youtube.com/watch?v=nBhBBFt9uTI&pp=0gcJCY0JAYcqIYzv", 171000, this);
        cutsceneContinueButton = new JButton("Continue");
        cutsceneContinueButton.addActionListener(this);
        add(cutsceneContinueButton);
        cutsceneContinueButton.setVisible(false);

        testInteractable = new Interactable(500, 500, "src\\images\\test.jpg", "Press 'E' to interact!", player);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setPanelToDisplay(Panel panelToDisplay) {
        this.panelToDisplay = panelToDisplay;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000./FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){ // updates info and draws screen
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                if (panelToDisplay != Panel.CUTSCENE_PLAYING && panelToDisplay != Panel.CUTSCENE_ENDED)
                    update();
                repaint();
                delta--;
            }
        }
    }

    public void update(){
        player.update();
        //enemy.update(player);

        for (Wall wall : walls)
            wall.update();

        testInteractable.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        switch (panelToDisplay) {
            case CUTSCENE_PLAYING -> paintCutscenePlaying(g);
            case CUTSCENE_ENDED -> paintCutsceneEnded(g);
            case LEVEL -> paintLevel(g);
        }
    }

    private void paintCutscenePlaying(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.RED);
        g.fillRoundRect(225, 250, 250, 125, 10, 10);
        g.setColor(Color.WHITE);
        g.fillPolygon(new int[] {325, 325, 400}, new int[] {265, 360, 312}, 3);

        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
        g.drawString("Watch the cutscene, dummy", 25, 500);
    }

    private void paintCutsceneEnded(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.RED);
        g.fillRoundRect(225, 250, 250, 125, 10, 10);
        g.setColor(Color.WHITE);
        g.fillPolygon(new int[] {325, 325, 400}, new int[] {265, 360, 312}, 3);

        g.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
        g.drawString("Cutscene Over", 200, 500);

        cutsceneContinueButton.setVisible(true);
        cutsceneContinueButton.setLocation(300, 600);
    }

    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        enemy.draw(g2);
        testInteractable.draw(g2);
        for (Wall wall : walls) {
            wall.draw(g2);
        }
    }

    public void makeWalls(){}
    public void makeSpikes(){}
    public void makeEnemies(){}

    public void reset() {
        player = new Player(350,300,this);

        walls.clear();
        makeWalls();
        spikes.clear();
        makeSpikes();
        enemies.clear();
        makeEnemies();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == cutsceneContinueButton) {
            setPanelToDisplay(Panel.LEVEL);
            cutsceneContinueButton.setVisible(false);
        }
    }
}