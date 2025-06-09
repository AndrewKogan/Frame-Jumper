import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FallingWall extends Wall implements ActionListener {
    private int speed;
    private Timer fallTimer;
    private boolean falling = false;

    public FallingWall(int x, int y, int width, int height, Player player, boolean wallJumpable, int speed, int time) {
        super(x, y, width, height, player, wallJumpable);
        this.speed = speed;
        fallTimer = new Timer(time, this);
    }

    @Override
    public void update() {
        if (hitBox.intersects(player.hitBox))
            fallTimer.start();
        if (falling)
            y += speed;

        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawRect(x,y,width,height);
        g2.setColor(Color.YELLOW);
        g2.fillRect(x+1,y+1,width-2,height-2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == fallTimer) {
            falling = true;
            fallTimer.stop();
        }
    }
}
