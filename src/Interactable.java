import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Interactable extends TriggerArea {
    private BufferedImage image;
    private String triggerAreaText;
    private Player playerReference;

    private int interactCooldown;

    public Interactable(int x, int y, String imagePath, String tooltip, Player p) {
        super(x, y, imagePath, p);
        triggerAreaText = tooltip;
        playerReference = p;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void update(){
        x -= playerReference.xspeed;
        y -= playerReference.yspeed;
        triggerArea = new Rectangle(x, y, image.getWidth(), image.getHeight());

        if (playerInTrigger() && KeyInputs.keysPressed[KeyEvent.VK_E])
            interact();
        if (interactCooldown >= 0)
            interactCooldown--;
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if (playerInTrigger()) {
            g2.drawString(triggerAreaText, x + 50, y - 25);
        }
    }

    //All subclasses must override this method
    protected void interact() {
        if (interactCooldown <= 0) {
            System.out.println("Interacted with!");
            interactCooldown = 20;
        }
    }
}
