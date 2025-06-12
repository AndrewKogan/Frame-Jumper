import java.awt.*;

public class LevelOne extends GamePanel {
    private AudioTriggerArea triggerArea1;
    private AudioTriggerArea triggerArea2;
    private AudioTriggerArea triggerArea3;
    private AudioTriggerArea triggerArea4;
    private AudioTriggerArea triggerArea5;

    public LevelOne() {
        super();
        levelEnd = new LevelEndInteractable(-940,-420,"src\\images\\test.jpg", player);

        triggerArea1 = new AudioTriggerArea(350, 400, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line1.wav");
        triggerArea2 = new AudioTriggerArea(-250, 400, 100, 100, player, "src\\Audio\\LevelOneVoicelines\\Line2.wav");
        triggerArea3 = new AudioTriggerArea(-450, 230, 100, 100, player, "src\\Audio\\LevelOneVoicelines\\Line3.wav");
        triggerArea4 = new AudioTriggerArea(200, 160, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line4.wav");
        triggerArea5 = new AudioTriggerArea(-540, -330, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line5.wav");
    }

    @Override
    public void update() {
        player.update();
        levelEnd.update();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
        for (Enemy enemy : enemies) enemy.update(player);

        triggerArea1.update();
        triggerArea2.update();
        triggerArea3.update();
        triggerArea4.update();
        triggerArea5.update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        levelEnd.draw(g2);
        for (Spikes spike : spikes) spike.draw(g2);
        for (Wall wall : walls) wall.draw(g2);
        for (Enemy enemy : enemies) enemy.draw(g2);
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
            spikes.add(new Spikes(i,524,"src\\images\\spikes.png",player));
        }
        for(int i = -50; i > -700; i-=50){
            spikes.add(new Spikes(i,54,"src\\images\\spikes.png",player));
        }
    }

    @Override
    public void makeEnemies() {
        enemies.add(new Enemy(150, 100, -205, 245, this));
    }

    @Override
    public void reset() {
        super.reset();
        levelEnd = new LevelEndInteractable(-940,-420,"src\\images\\test.jpg", player);
        triggerArea1 = new AudioTriggerArea(350, 400, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line1.wav");
        triggerArea2 = new AudioTriggerArea(-250, 400, 100, 100, player, "src\\Audio\\LevelOneVoicelines\\Line2.wav");
        triggerArea3 = new AudioTriggerArea(-450, 230, 100, 100, player, "src\\Audio\\LevelOneVoicelines\\Line3.wav");
        triggerArea4 = new AudioTriggerArea(200, 160, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line4.wav");
        triggerArea5 = new AudioTriggerArea(-540, -330, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line5.wav");
    }
}
