import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.signum;

public class Player {
    GamePanel panel;
    int x;
    int y;
    int width;
    int height;

    double xspeed;
    double gravity;
    double yspeed;

    boolean ycollision;
    boolean xcollision;
    

    Rectangle hitBox;

    public Player(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y =y;
        ycollision = false;
        xcollision = false;
        xspeed = 5;
        yspeed = -5;
        gravity = 0.3;
        width = 50;
        height = 100;
        hitBox = new Rectangle(x,y,width,height);
    }

    public void update() {
        if (!(KeyInputs.keysPressed[KeyEvent.VK_D] && KeyInputs.keysPressed[KeyEvent.VK_A])) {
            if (KeyInputs.keysPressed[KeyEvent.VK_D]) {
                xspeed = 5;
            }
            else if (KeyInputs.keysPressed[KeyEvent.VK_A]) {
                xspeed = -5;
            }
            else{
                xspeed = 0;
            }
        }
        if (KeyInputs.keysPressed[KeyEvent.VK_W]) {
            if(ycollision) yspeed = -7;
        }
        yspeed+=gravity;

        //Horizontal collision
        xcollision = false;
        hitBox.x+=xspeed;
        for(Wall wall: panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.x-=xspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x-=Math.signum(xspeed);
                xcollision = true;
                xspeed = 0;
                x = hitBox.x;
            }
        }

        //Vertical collision
        ycollision = false;
        hitBox.y+=yspeed;
        for(Wall wall: panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.y-=yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y-=Math.signum(yspeed);
                ycollision = true;
                yspeed = 0;
                y = hitBox.y;
            }
        }

        x+=xspeed;
        y+=yspeed;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(x,y,width,height);
    }
}
