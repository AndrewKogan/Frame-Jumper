import java.awt.*;
import GridPuzzle.GridPuzzle;

public class LevelThree extends GamePanel {

    public LevelThree() {
        super();
        levelEnd = new LevelEndInteractable(4200,1308,"src\\images\\test.jpg", player);
        gridPuzzleStart = new GridPuzzleInteractable(2850,108,"src\\images\\test.jpg", player);
    }

    @Override
    public void update() {
        player.update();
        gridPuzzleStart.update();
        levelEnd.update();
        if(GridPuzzle.solved) door.open();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
        for (int i = 0; i < enemies.size(); i++) enemies.get(i).update(player);
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        levelEnd.draw(g2);
        gridPuzzleStart.draw(g2);
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).draw(g2);
        for (Wall wall : walls) wall.draw(g2);
        for(int i = 0; i < enemies.size(); i++) enemies.get(i).draw(g2);
        player.draw(g2);
    }

    @Override
    public void makeWalls() {
        walls.add(new FallingWall(300,500,100,50,player,false,3,100));
        walls.add(new Wall(600,500,100,50,player,false));
        walls.add(new Wall(900,500,10,50,player,false));
        walls.add(new FallingWall(1000,-1000,50,1500,player,true,2,300));
        walls.add(new Wall(800,-200,50,450,player,true));
        walls.add(new Wall(1050,100,100,50,player,false));
        walls.add(new FallingWall(1350,100,10,50,player,false, 10, 100));
        walls.add(new FallingWall(1600,150,10,50,player,false, 10, 100));
        walls.add(new FallingWall(1850,200,10,50,player,false, 10, 100));
        walls.add(new FallingWall(2100,250,10,50,player,false, 10, 100));
        walls.add(new FallingWall(2350,300,10,50,player,false, 10, 100));
        walls.add(new Wall(2600,300,10,50,player,false));
        walls.add(new Wall(2850,300,300,50,player,false));
        door = new Door(3100,-50,50,350,player,false);
        walls.add(door);
        walls.add(new Wall(1350,700,1650,50,player,false));
        walls.add(new FallingWall(3300,300,100,50,player,false,1,10));
        walls.add(new FallingWall(3600,700,100,50,player,false,1,10));
        walls.add(new FallingWall(3900,1100,100,50,player,false,1,10));
        walls.add(new Wall(4200,1500,300,50,player,false));
    }

    @Override
    public void makeSpikes(){
        spikes.add(new Spikes(802,-224,"src\\images\\spikes.png",player));
        for(int i = -1000; i < 1500; i+=46){
            if(!(i>400 && i<650)) spikes.add(new Spikes(3474,i,"src\\images\\leftspikes.png",player));
        }
        for(int i = -1000; i < 1500; i+=46){
            if(!(i>800 && i<1050)) spikes.add(new Spikes(3774,i,"src\\images\\leftspikes.png",player));
        }
        for(int i = -1000; i < 1500; i+=46){
            if(!(i>1200 && i<1450)) spikes.add(new Spikes(4074,i,"src\\images\\leftspikes.png",player));
        }
    }

    @Override
    public void makeEnemies(){
        for(int i = 0; i<20; i++){
            enemies.add(new Enemy(1350+i*80,650,1350,2950, this));
        }
    }

    @Override
    public void reset() {
        super.reset();
        gridPuzzleStart = new GridPuzzleInteractable(2850,108,"src\\images\\test.jpg",player);
        levelEnd = new LevelEndInteractable(4200,1308,"src\\images\\test.jpg", player);
        GridPuzzle.solved = false;
        GridPuzzle.opened = false;
    }
}
