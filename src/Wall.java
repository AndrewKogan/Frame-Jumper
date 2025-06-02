import java.awt.*;

public class Wall {
    int x;
    int y;
    int width;
    int height;
    Player player;

    Rectangle hitBox;

    public Wall(int x, int y, int width, int height, Player player){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;

        hitBox = new Rectangle(x,y,width,height);
    }

    public void update() {
        x -= player.xspeed;
        y -= player.yspeed;
        hitBox =  new Rectangle(x,y,width,height);
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.drawRect(x,y,width,height);
        g2.setColor(Color.WHITE);
        g2.fillRect(x+1,y+1,width-2,height-2);
    }
}
