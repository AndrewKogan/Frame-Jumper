import java.awt.*;

public class Door extends Wall {
    public boolean opened;
    public boolean isOpening;
    private int startY;
    private int raiseHeight;

    public Door(int x, int y, int width, int height, Player player, boolean wallJumpable) {
        super(x, y, width, height, player, wallJumpable);
        startY = y;
    }

    @Override
    public void update() {
        if(!isOpening) super.update();
        else{
            x -= player.xspeed;
            hitBox =  new Rectangle(x,y,width,height);
        }
        if(!isOpening && !opened){
            startY = y;
        }
        if (isOpening) {
            y -= 100 / 60;
        }
        if (y <= (startY - 100)) {
            opened = true;
            isOpening = false;
        }
    }

    public void open() {
        if (!opened){
            isOpening = true;
        }
    }
}
