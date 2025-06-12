import java.awt.*;

public class LevelFour extends GamePanel {
    private Boss boss;

    public LevelFour() {
        super();
        this.setPreferredSize(new Dimension(1500, 1000));

        player.x = 750;
        player.y = 800;
        boss = new Boss(player);
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
