import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class TriggerArea {
    protected int x, y;
    private int width, height;
    protected Rectangle triggerArea;
    protected Player playerReference;

    protected BufferedImage image;

    private boolean triggerToggle;

    public TriggerArea(int x, int y, int width, int height, Player p) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        triggerArea = new Rectangle(x, y, width, height);
        playerReference = p;
    }

    public TriggerArea(int x, int y, String imagePath, Player p) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assert image != null;
        width = image.getWidth();
        height = image.getHeight();
        triggerArea = new Rectangle(x, y, width, height);
        playerReference = p;
    }

    public void update() {
        x -= playerReference.xspeed;
        y -= playerReference.yspeed;
        triggerArea = new Rectangle(x, y, width, height);

        if (playerInTrigger()) {
            onTriggerStay();
            if (!triggerToggle) {
                triggerToggle = true;
                onTriggerEnter();
            }
        }
        else
            triggerToggle = false;
    }

    public void draw(Graphics2D g2) {
        if (image != null)
            g2.drawImage(image, x, y, null);
    }

    public boolean playerInTrigger() {
        return triggerArea.intersects(playerReference.hitBox);
    }

    protected void onTriggerEnter() {
        //Do whatever here
    }

    protected void onTriggerStay() {
        //Do whatever here
    }
}
