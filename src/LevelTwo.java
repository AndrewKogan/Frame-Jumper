import java.awt.*;
import CodePuzzle.CodePuzzle;

public class LevelTwo extends GamePanel {
    private Door door;

    public LevelTwo() {
        super();
        levelEnd = new LevelEndInteractable(790,670,"src\\images\\test.jpg", player);
        codePuzzleStart = new CodePuzzleInteractable(763,408,"src\\images\\test.jpg",player);
    }

    @Override
    public void update() {
        levelEnd.update();
        if(CodePuzzle.solved) door.open();
        codePuzzleStart.update();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
        player.update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        levelEnd.draw(g2);
        codePuzzleStart.draw(g2);
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).draw(g2);
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
        walls.add(new Wall(1025,600,50,400,player,false));

        door = new Door(675,350,50,250,player,false);
        walls.add(door);

        walls.add(new Wall(525,700,150,50,player,false));
        walls.add(new Wall(625,750,50,100,player,true));
        walls.add(new Wall(825,850,150,50,player,false));
    }

    @Override
    public void makeSpikes(){
        for(int i = 180; i > -200; i-=50){
            spikes.add(new Spikes(i,110,"src\\images\\spikes.png",player));
        }
        for(int i = 50; i < 150; i+=50){
            spikes.add(new Spikes(1300,i,"src\\images\\leftspikes.png",player));
        }
        for(int i = 400; i < 550; i+=50){
            spikes.add(new Spikes(430,i,"src\\images\\leftspikes.png",player));
        }
        for(int i = 448; i < 1080; i+=46){
            spikes.add(new Spikes(i,156,"src\\images\\spikes.png",player));
        }
    }

    @Override
    public void reset() {
        super.reset();
        codePuzzleStart = new CodePuzzleInteractable(763,408,"src\\images\\test.jpg",player);
        levelEnd = new LevelEndInteractable(790,670,"src\\images\\test.jpg", player);
        CodePuzzle.solved = false;
    }
}
