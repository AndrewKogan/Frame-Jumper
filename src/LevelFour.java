import java.awt.*;

public class LevelFour extends GamePanel {
    private Boss boss;

    private Cutscene introCutscene;
    private Cutscene postCutscene;

    public LevelFour() {
        super();
        this.setPreferredSize(new Dimension(1500, 1000));

        player.x = 750;
        player.y = 800;
        boss = new Boss(player);

        introCutscene = new Cutscene("https://youtube.com/shorts/O2MkW4CmCjc?feature=share", 48000, this);
        postCutscene = new Cutscene("", 0, this);
    }

    @Override
    public void startGameThread() {
        super.startGameThread();
        //introCutscene.play();
    }

    @Override
    public void update() {
        player.update();
        boss.update();
        for (Wall wall : walls) wall.update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for (Wall wall : walls) wall.draw(g2);
        boss.draw(g2);
        player.draw(g2);
    }

    @Override
    public void makeWalls() {
        walls.add(new Wall(0,900,1500,1000,player,false));
        walls.add(new Wall(-950, -200, 1000, 2000, player, false));
        walls.add(new Wall(1450, -200, 1000, 2000, player, false));
    }

    @Override
    public void reset() {
        super.reset();
        player.x = 750;
        player.y = 800;
        boss = new Boss(player);
    }
}
