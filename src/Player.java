import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Math.signum;

public class Player {
    GameParent panel;
    GamePanel firstPanel;
    SecondGamePanel secondPanel;
    boolean isFirstPanel;
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

    public Player(int x, int y, GameParent panel){
        this.panel = panel;
        this.x = x;
        this.y = y;
        ycollision = false;
        xcollision = false;
        xspeed = 0;
        yspeed = 0;
        gravity = 0.3;
        width = 50;
        height = 100;
        hitBox = new Rectangle(x,y,width,height);
        if(panel instanceof GamePanel){
            firstPanel = (GamePanel) panel;
            isFirstPanel = true;
        }
        if(panel instanceof SecondGamePanel){
            secondPanel = (SecondGamePanel) panel;
            isFirstPanel = false;
        }
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
        if(isFirstPanel){
            for(Wall wall: firstPanel.walls) {
                if (hitBox.intersects(wall.hitBox)) {
                    hitBox.x -= xspeed;
                    while (!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                    hitBox.x -= Math.signum(xspeed);
                    xcollision = true;
                    xspeed = 0;
                    x = hitBox.x;
                }
            }
        }
        else{
            for(Wall wall: secondPanel.walls) {
                if (hitBox.intersects(wall.hitBox)) {
                    hitBox.x -= xspeed;
                    while (!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                    hitBox.x -= Math.signum(xspeed);
                    xcollision = true;
                    xspeed = 0;
                    x = hitBox.x;
                }
            }
        }

        //Vertical collision
        ycollision = false;
        hitBox.y+=yspeed;
        if(isFirstPanel) {
            for (Wall wall : firstPanel.walls) {
                if (hitBox.intersects(wall.hitBox)) {
                    hitBox.y -= yspeed;
                    while (!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                    hitBox.y -= Math.signum(yspeed);
                    ycollision = true;
                    yspeed = 0;
                    y = hitBox.y;
                }
            }
        }
        else{
            for (Wall wall : secondPanel.walls) {
                if (hitBox.intersects(wall.hitBox)) {
                    hitBox.y -= yspeed;
                    while (!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);
                    hitBox.y -= Math.signum(yspeed);
                    ycollision = true;
                    yspeed = 0;
                    y = hitBox.y;
                }
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
