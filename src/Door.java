import Audio.AudioPlayer;

import java.awt.*;

public class Door extends Wall {
    public boolean opened;
    public boolean isOpening;
    private int startY;

    public Door(int x, int y, int width, int height, Player player, boolean wallJumpable) {
        super(x, y, width, height, player, wallJumpable);
        startY = y;
    }

    @Override
    public void update() {
        if(!isOpening) super.update();
        else {
            x -= player.xspeed;
            hitBox =  new Rectangle(x,y,width,height);
        }
        if (!isOpening && !opened) {
            startY = y;
        }
        if (isOpening) {
            y -= 100 / GamePanel.FPS;
        }
        if (y <= (startY - 100)) {
            opened = true;
            isOpening = false;
            player.lockMovement = false;
        }
    }

    public void open() {
        if (!opened && !isOpening) {
            isOpening = true;
            player.lockMovement = true;
            AudioPlayer.playSound("src\\Audio\\DoorOpen.wav");
        }
    }
}
