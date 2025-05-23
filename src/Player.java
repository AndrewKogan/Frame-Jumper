import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    GamePanel panel;
    int x;
    int y;
    int width;
    int height;

    int speed;
    

    Rectangle hitBox;

    public Player(int x, int y, GamePanel panel){
        this.panel = panel;
        this.x = x;
        this.y =y;
        speed = 5;

        width = 50;
        height = 100;
        hitBox = new Rectangle(x,y,width,height);
    }

    public void update() {
        if (KeyInputs.upPressed) {
            y -= speed;
        }
        if (KeyInputs.downPressed) {
            y += speed;
        }
        if (!(KeyInputs.rightPressed && KeyInputs.keysPressed[KeyEvent.VK_A])) {
            if (KeyInputs.rightPressed) {
                x += speed;
//                widthModifier = 0;
//                widthScaler = 2;
//                selectedRow = 7;
//                selectedColumn = runFrame;
//                if (frameCount % 5 == 0) {
//                    if (runFrame == 11) {
//                        runFrame = 0;
//                    } else {
//                        runFrame++;
//                    }
//                }
            }
            if (KeyInputs.keysPressed[KeyEvent.VK_A]) {
                x -= speed;
//                widthModifier = 2 * spriteWidth;
//                widthScaler = -2;
//                selectedRow = 7;
//                selectedColumn = runFrame;
//                if (frameCount % 5 == 0) {
//                    if (runFrame == 11) {
//                        runFrame = 0;
//                    } else {
//                        runFrame++;
//                    }
//                }
            }
        }
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(x,y,width,height);
    }
    //37:56
}
