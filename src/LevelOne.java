import java.awt.*;

public class LevelOne extends GamePanel {
    private Cutscene introCutscene;

    private AudioTriggerArea triggerArea1;
    private AudioTriggerArea triggerArea2;
    private AudioTriggerArea triggerArea3;
    private AudioTriggerArea triggerArea4;
    private AudioTriggerArea triggerArea5;

    public LevelOne() {
        super();
        levelEnd = new LevelEndInteractable(-940,-420,"src\\images\\test.jpg", player);

        introCutscene = new Cutscene("https://youtube.com/shorts/5V9fezUeX4s?feature=share", 77000, this);

        triggerArea1 = new AudioTriggerArea(300,499,100,50, player, "src\\Audio\\LevelOneVoicelines\\Line1.wav", 0,15*60);
        triggerArea2 = new AudioTriggerArea(-250,499,100,50, player, "src\\Audio\\LevelOneVoicelines\\Line2.wav", 0,11*60);
        triggerArea3 = new AudioTriggerArea(-450, 230, 100, 100, player, "src\\Audio\\LevelOneVoicelines\\Line3.wav", 0,14*60);
        triggerArea4 = new AudioTriggerArea(200, 160, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line4.wav", 0,8*60);
        triggerArea5 = new AudioTriggerArea(-540, -330, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line5.wav", 0,8*60);

    }

    @Override
    public void startGameThread() {
        super.startGameThread();
        introCutscene.play();
    }

    @Override
    public void update() {
        player.update();
        levelEnd.update();
        for (Wall wall : walls) wall.update();
        for (int i = 0; i < spikes.size(); i++) spikes.get(i).update();
        for (Enemy enemy : enemies) enemy.update(player);

        if(triggerArea1!=null)triggerArea1.update();
        if(triggerArea2!=null)triggerArea2.update();
        if(triggerArea3!=null)triggerArea3.update();
        if(triggerArea4!=null)triggerArea4.update();
        if(triggerArea5!=null)triggerArea5.update();
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
        if(triggerArea1!=null && !triggerArea1.alreadyPlayed) triggerArea1 = new AudioTriggerArea(300,499,100,50, player, "src\\Audio\\LevelOneVoicelines\\Line1.wav", 0,15*60);
        else triggerArea1 = null;

        if(triggerArea2!=null && !triggerArea2.alreadyPlayed) triggerArea2 = new AudioTriggerArea(-250,499,100,50, player, "src\\Audio\\LevelOneVoicelines\\Line2.wav", 0,11*60);
        else triggerArea2 = null;

        if(triggerArea3!=null && !triggerArea3.alreadyPlayed) triggerArea3 = new AudioTriggerArea(-450, 230, 100, 100, player, "src\\Audio\\LevelOneVoicelines\\Line3.wav", 0,14*60);
        else triggerArea3 = null;

        if(triggerArea4!=null && !triggerArea4.alreadyPlayed) triggerArea4 = new AudioTriggerArea(200, 160, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line4.wav", 0,8*60);
        else triggerArea4 = null;

        if(triggerArea5!=null && !triggerArea5.alreadyPlayed) triggerArea5 = new AudioTriggerArea(-540, -330, 50, 100, player, "src\\Audio\\LevelOneVoicelines\\Line5.wav", 0,8*60);
        else triggerArea5 = null;
    }
}
