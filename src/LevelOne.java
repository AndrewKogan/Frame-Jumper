import java.awt.*;

public class LevelOne extends GamePanel {

    public LevelOne() {
        super();
    }

    @Override
    public void update() {
        player.update();

        for (Wall wall : walls)
            wall.update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        for (Wall wall : walls) {
            wall.draw(g2);
        }
    }

    @Override
    public void makeWalls() {

    }

    @Override
    public void reset() {
        super.reset();
    }
}
