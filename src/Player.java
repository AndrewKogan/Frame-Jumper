import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.signum;

public class Player {
    GamePanel panel;

    int x;
    int y;
    int width;
    int height;

    int xspeed;
    int gravity;
    int yspeed;
    int frameCount;
    int cooldown;

    boolean ycollision;
    boolean xcollision;

    boolean holdingOn;
    int gravityDecelerator;

    Rectangle hitBox;

    public Player(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;
        ycollision = false;
        xcollision = false;
        xspeed = 0;
        yspeed = 0;
        gravity = 1;
        width = 50;
        height = 100;
        hitBox = new Rectangle(x,y,width,height);
        frameCount = 0;
        cooldown = 0;
        holdingOn = false;
        gravityDecelerator = 1;
    }

    public void update() {
        frameCount++;
        cooldown--;
        if (!(KeyInputs.keysPressed[KeyEvent.VK_D] && KeyInputs.keysPressed[KeyEvent.VK_A]) && cooldown < 20) {
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
            if(ycollision){
                yspeed = -7;
                cooldown = 10;
            }
            else if(xcollision && cooldown <= 0){
                yspeed = -7;
                xspeed = (int) (-xspeed * 1.4);
                gravityDecelerator = 2;
                cooldown = 27;
            }
        }
        if(frameCount%(3*gravityDecelerator)==0) yspeed+=gravity;

        if(xspeed!=0) holdingOn = true;


        //Horizontal collision
        xcollision = false;
        hitBox.x=x+xspeed;
        for(Wall wall: panel.walls) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.x -= xspeed;
                while (!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                xcollision = true;
                xspeed = 0;
                x = hitBox.x;
            }
        }

        //Vertical collision
        ycollision = false;
        hitBox.y=y+yspeed;
        for (Wall wall : panel.walls) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.y -= yspeed;
                while (!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                hitBox.y -= Math.signum(yspeed);
                ycollision = true;
                yspeed = 0;
                y = hitBox.y;
            }
        }

        gravityDecelerator = 1;
        if(xcollision && holdingOn){
            gravityDecelerator = 3;
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(x,y,width,height);
    }
}
