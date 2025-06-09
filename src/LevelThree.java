import java.awt.*;
import CodePuzzle.CodePuzzle;

public class LevelThree extends GamePanel {
    private Door door;

    public LevelThree() {
        super();
        levelEnd = new LevelEndInteractable(790,670,"src\\images\\test.jpg", player);
        codePuzzleStart = new CodePuzzleInteractable(763,408,"src\\images\\test.jpg",player);
    }

    @Override
    public void update() {
        player.update();
        levelEnd.update();
        if(CodePuzzle.solved) door.open();
        codePuzzleStart.update();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
        for(Enemy enemy : enemies) enemy.update(player);
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        levelEnd.draw(g2);
        codePuzzleStart.draw(g2);
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).draw(g2);
        for (Wall wall : walls) wall.draw(g2);
        for(Enemy enemy : enemies) enemy.draw(g2);
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


    }

    @Override
    public void makeSpikes(){

    }

    @Override
    public void makeEnemies(){

    }

    @Override
    public void reset() {
        super.reset();
        codePuzzleStart = new CodePuzzleInteractable(763,408,"src\\images\\test.jpg",player);
        levelEnd = new LevelEndInteractable(790,670,"src\\images\\test.jpg", player);
        CodePuzzle.solved = false;
    }
}
