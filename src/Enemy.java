import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.incrementExact;
import static java.lang.Math.signum;

public class Enemy {
    GamePanel panel;
    int x;
    int y;
    int width;
    int height;

    int xspeed;
    int gravity;
    int yspeed;
    int frameCount;

    boolean ycollision;
    boolean xcollision;

    boolean touchedPlayer;
    int invincibilityFrames;


    Rectangle hitBox;

    public Enemy(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y =y;
        ycollision = false;
        xcollision = false;
        xspeed = 0;
        yspeed = 0;
        gravity = 1;
        width = 50;
        height = 50;
        frameCount = 0;
        hitBox = new Rectangle(x,y,width,height);
        touchedPlayer = false;
        invincibilityFrames = 0;
    }

    public void update(Player player) {
        frameCount++;
        if (player.x > x) {
            xspeed = 2;
        }
        else if (player.x<x) {
            xspeed = -2;
        }
        else{
            xspeed = 0;
        }
        if(frameCount%3==0) {
            yspeed += gravity;
        }
        //Horizontal collision
        xcollision = false;
        hitBox.x=x+xspeed;
        for (Wall wall : panel.walls) {
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

        //Player collision + invincibility frames
        if(!touchedPlayer) {
            if (hitBox.intersects(player.hitBox)) {
                System.out.println("you got touched!");
                touchedPlayer = true;
                invincibilityFrames = 40;
            }
        }
        invincibilityFrames--;
        if(invincibilityFrames<=0) touchedPlayer = false;

        x+=xspeed-player.xspeed;
        y+=yspeed-player.yspeed;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fillRect(x,y,width,height);
        g2.setColor(Color.BLACK);
        g2.drawRect(hitBox.x, hitBox.y, width, height);
    }
}
