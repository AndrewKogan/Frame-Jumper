import java.awt.*;
import java.awt.event.KeyEvent;

public class Interactable extends TriggerArea {
    private String triggerAreaText;

    private int interactCooldown;

    public Interactable(int x, int y, String imagePath, String tooltip, Player p) {
        super(x, y, imagePath, p);
        triggerAreaText = tooltip;
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
