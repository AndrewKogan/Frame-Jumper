import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Interactable {

    private BufferedImage image;
    private int x, y;
    private String triggerAreaText;
    private Rectangle triggerArea;
    private Player playerReference;

    private int interactCooldown;

    public Interactable(int x, int y, String imagePath, String tooltip, Player p) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        triggerAreaText = tooltip;
        triggerArea = new Rectangle(x, y, image.getWidth(), image.getHeight());
        playerReference = p;
    }

    public boolean playerInTrigger() {
        return triggerArea.intersects(playerReference.hitBox);
    }

    public void update(){
        x -= playerReference.xspeed;
        y -= playerReference.yspeed;
        triggerArea = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
        if (playerInTrigger()) {
            g2.drawString(triggerAreaText, x + 50, y - 25);
        }
        interactCooldown--;
    }

    //All subclasses must override this method
    public void interact() {
        if (interactCooldown <= 0) {
            System.out.println("Interacted with!");
            interactCooldown = 20;
        }
    }
}
