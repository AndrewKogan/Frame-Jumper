import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static java.lang.Math.*;

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
    boolean wallJumpedRight;
    boolean wallJumpedLeft;
    Wall wallCollided;

    Rectangle hitBox;

    boolean facingLeft;
    boolean lockMovement;

    private Animation idle;
    private Animation run;
    private Animation jump;
    private Animation fall;
    private Animation attack;
    private Animation block;
    private Animation wallSlide;
    private Animation wallJump;
    private Animation hang;
    private Animation climb;
    public Animation death;
    public Animation currentAnimation;

    public Player(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y = y;
        facingLeft = true;
        ycollision = false;
        xcollision = false;
        lockMovement = false;
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
        wallJumpedRight = false;
        wallJumpedLeft = false;
        wallCollided = new Wall(0,0,0,0,this,false);

        idle = new Animation("src\\images\\Andrew\\Idle", 4, 4, true);
        run = new Animation("src\\images\\Andrew\\Run", 4, 8, true);
        jump = new Animation("src\\images\\Andrew\\Jump", 3, 15, false);
        fall = new Animation("src\\images\\Andrew\\Fall", 1, 1, false);
        wallSlide = new Animation("src\\images\\Andrew\\Sliding", 1, 1, false);
        wallJump = new Animation("src\\images\\Andrew\\WallJump", 3, 15, false);
        hang = new Animation("src\\images\\Andrew\\Hanging", 1, 1, false);
        climb = new Animation("src\\images\\Andrew\\Climbing", 4, 8, true);
        attack = new Animation("src\\images\\Andrew\\Attack", 5, 8, false);
        block = new Animation("src\\images\\Andrew\\Block", 2, 10, false);
        death = new Animation("src\\images\\Andrew\\Death", 4, 2, false);

        currentAnimation = idle;
        currentAnimation.play();
    }

    public void update() {
        frameCount++;
        cooldown--;

        if(frameCount%(3*gravityDecelerator)==0) yspeed+=gravity;
        if(panel.door != null && panel.door.isOpening) lockMovement = true;

        if (!(KeyInputs.keysPressed[KeyEvent.VK_D] && KeyInputs.keysPressed[KeyEvent.VK_A])) {
            if (KeyInputs.keysPressed[KeyEvent.VK_D] && !wallJumpedRight) {
                facingLeft = false;
                xspeed = 5;
                if (ycollision) {
                    if (wallCollided.y > y && currentAnimation != run) {
                        currentAnimation = run;
                        currentAnimation.play();
                    }
                    else if (wallCollided.y < y && currentAnimation != climb) {
                        currentAnimation = climb;
                        currentAnimation.play();
                    }
                }
            }
            else if (KeyInputs.keysPressed[KeyEvent.VK_A] && !wallJumpedLeft) {
                facingLeft = true;
                xspeed = -5;
                if (ycollision) {
                    if (wallCollided.y > y && currentAnimation != run) {
                        currentAnimation = run;
                        currentAnimation.play();
                    }
                    else if (wallCollided.y < y && currentAnimation != climb) {
                        currentAnimation = climb;
                        currentAnimation.play();
                    }
                }
            }
            else if (!(wallJumpedLeft || wallJumpedRight)) {
                xspeed = 0;
                if (ycollision) {
                    if (wallCollided.y > y && currentAnimation != idle) {
                        currentAnimation = idle;
                        currentAnimation.play();
                    }
                    else if (wallCollided.y < y && currentAnimation != hang) {
                        currentAnimation = hang;
                    }
                }
            }
        }

        if(xspeed!=0) holdingOn = true;

        if (KeyInputs.keysPressed[KeyEvent.VK_W]) {
            if(ycollision){
                yspeed = -7;
                cooldown = 20;
                if(currentAnimation!=climb&&currentAnimation!=hang) {
                    currentAnimation = jump;
                    currentAnimation.play();
                }
            }
            else if(xcollision && cooldown <= 0 && wallCollided.wallJumpable){
                yspeed = -10;
                if(xspeed > 0){
                    wallJumpedRight = true;
                    wallJumpedLeft = false;
                    facingLeft = true;
                }
                if(xspeed < 0){
                    wallJumpedLeft = true;
                    wallJumpedRight = false;
                    facingLeft = false;
                }
                currentAnimation = wallJump;
                currentAnimation.play();
                xspeed = (int) (-xspeed * 0.7);

                gravityDecelerator = 1;
            }
        }

        //Horizontal collision
        xcollision = false;
        hitBox.x=x+xspeed;
        for(Wall wall: panel.walls) {
            if (hitBox.intersects(wall.hitBox)) {
                hitBox.x -= xspeed;
                while (!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);
                hitBox.x -= Math.signum(xspeed);
                wallCollided = wall;
                xcollision = true;
                xspeed = 0;
                x = hitBox.x;

                if (wall instanceof FallingWall fallingWall)
                    fallingWall.startFall();
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
                wallCollided = wall;
                ycollision = true;
                yspeed = 0;
                y = hitBox.y;

                if (wall instanceof FallingWall fallingWall)
                    fallingWall.startFall();
            }
        }

        gravityDecelerator = 1;
        if(xcollision && holdingOn){
            gravityDecelerator = 3;
            currentAnimation = wallSlide;
        }
        else if (yspeed>0){
            currentAnimation = fall;
        }
        if(ycollision){
            wallJumpedLeft = false;
            wallJumpedRight = false;
        }

        if(lockMovement) {
            yspeed = 0;
            xspeed = 0;
        }
        if (KeyInputs.keysPressed[KeyEvent.VK_ESCAPE])
            panel.reset();
    }

    public void draw(Graphics2D g2) {
        BufferedImage currentImage = currentAnimation.getActiveFrame();

        if (xspeed == 0 && currentAnimation == climb) currentImage = currentAnimation.getFirstFrame();
        if((facingLeft && !(currentAnimation == wallSlide)) || (!facingLeft && (currentAnimation == wallSlide))) g2.drawImage(currentImage, x, y, width, height,null);
        else g2.drawImage(currentImage, x+width, y, width*-1, height,null);
        /*g2.setColor(Color.BLACK);
        g2.fillRect(x,y,width,height);*/
    }
}
