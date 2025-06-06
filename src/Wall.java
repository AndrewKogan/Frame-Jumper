import java.awt.*;

public class Wall {
    int x;
    int y;
    int width;
    int height;
    Player player;
    boolean wallJumpable;

    Rectangle hitBox;

    public Wall(int x, int y, int width, int height, Player player, boolean wallJumpable){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.player = player;
        this.wallJumpable = wallJumpable;

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
        if(wallJumpable) g2.setColor(Color.GREEN);
        g2.fillRect(x+1,y+1,width-2,height-2);
    }
    public void open(){}
}
