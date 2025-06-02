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
        walls.add(new Wall(300,500,100,50,player,false));
        walls.add(new Wall(25,500,100,50,player,false));
        walls.add(new Wall(-250,500,100,50,player,false));
        walls.add(new Wall(-475,330,200,50,player,false));
        walls.add(new Wall(-630,400,100,50,player,false));
        walls.add(new Wall(-200,250,500,50,player,false));
        walls.add(new Wall(350,50,50,150,player,true));
        walls.add(new Wall(0,30,200,50,player,false));
        walls.add(new Wall(-250,-170,200,50,player,false));
        walls.add(new Wall(-540,-230,200,50,player,false));
//        new LevelEndInteractable(-540,-350,"","Press E to end the level", player);
//        walls.add(new Wall(-540,-300,200,50,player,false));
    }
}
