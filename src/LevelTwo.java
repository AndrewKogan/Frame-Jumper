import java.awt.*;

public class LevelTwo extends GamePanel {
    public LevelTwo() {
        super();
//        levelEnd = new LevelEndInteractable(,,"src\\images\\test.jpg","Press E to end the level", player);
    }

    @Override
    public void update() {
//        levelEnd.update();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
        player.update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
//        levelEnd.draw(g2);
        for (Spikes spike : spikes) spike.draw(g2);
        for (Wall wall : walls) wall.draw(g2);
        player.draw(g2);
    }

    @Override
    public void makeWalls() {
        walls.add(new Wall(300,500,100,50,player,false));
        walls.add(new Wall(225,100,50,300,player,true));
        walls.add(new Wall(425,150,50,250,player,true));
        walls.add(new Wall(425,100,150,50,player,false));
        walls.add(new Wall(800,100,150,50,player,false));
        walls.add(new Wall(1125,180,50,50,player,false));
        walls.add(new Wall(1325,50,50,350,player,false));
        walls.add(new Wall(1325,400,50,100,player,true));
        walls.add(new Wall(725,600,300,50,player,false));
    }

    @Override
    public void makeSpikes(){
        for(int i = 225; i > -400; i-=50){
            spikes.add(new Spikes(i,110,player));
        }
    }

    @Override
    public void reset() {
        super.reset();
//        levelEnd = new LevelEndInteractable(,,"src\\images\\test.jpg","Press E to end the level", player);
    }
}
