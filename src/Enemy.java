import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.incrementExact;
import static java.lang.Math.signum;

public class Enemy {
    GamePanel panel;
    int x;
    int y;
    int minX, maxX;
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

    public Enemy(int x, int y, int minX, int maxX, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;
        this.minX = minX;
        this.maxX = maxX;
        ycollision = false;
        xcollision = false;
        xspeed = 3;
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

        if (x < minX || x > maxX) {
            if (x < minX) x = minX;
            if (x > maxX) x = maxX;
            xspeed *= -1;
        }

        if(frameCount%3==0) {
            yspeed += gravity;
            frameCount = 0;
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
                //xspeed = 0;
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
                panel.reset();
                invincibilityFrames = 40;
            }
        }
        invincibilityFrames--;
        if(invincibilityFrames<=0) touchedPlayer = false;

        x+=xspeed-player.xspeed;
        minX -= player.xspeed;
        maxX -= player.xspeed;
        y+=yspeed-player.yspeed;
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED);
        g2.fillRect(x,y,width,height);
    }
}
