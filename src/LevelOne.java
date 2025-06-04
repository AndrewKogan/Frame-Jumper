import java.awt.*;

public class LevelOne extends GamePanel {
    public LevelOne() {
        super();
        levelEnd = new LevelEndInteractable(-940,-420,"src\\images\\test.jpg", player);
    }

    @Override
    public void update() {
        player.update();
        levelEnd.update();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        levelEnd.draw(g2);
        for (Spikes spike : spikes) spike.draw(g2);
        for (Wall wall : walls) wall.draw(g2);
        player.draw(g2);
    }

    @Override
    public void makeWalls() {
        walls.add(new Wall(300,500,100,50,player,false));
        walls.add(new Wall(25,500,100,50,player,false));
        walls.add(new Wall(-250,500,100,50,player,false));
        walls.add(new Wall(-475,330,200,50,player,false));
        walls.add(new Wall(-630,400,100,50,player,false));
        walls.add(new Wall(-200,260,500,50,player,false));
        walls.add(new Wall(350,50,50,150,player,true));
        walls.add(new Wall(0,30,200,50,player,false));
        walls.add(new Wall(-250,-170,200,50,player,false));
        walls.add(new Wall(-540,-230,200,50,player,false));
        walls.add(new Wall(-940,-230,200,50,player,false));
    }

    @Override
    public void makeSpikes(){
        for(int i = 250; i > -700; i-=50){
            spikes.add(new Spikes(i,524,player, 0));
        }
        for(int i = -50; i > -700; i-=50){
            spikes.add(new Spikes(i,54,player, 0));
        }
    }

    @Override
    public void reset() {
        super.reset();
        levelEnd = new LevelEndInteractable(-940,-420,"src\\images\\test.jpg", player);
    }
}
