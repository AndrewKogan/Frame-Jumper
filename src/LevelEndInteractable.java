import java.awt.event.KeyEvent;

public class LevelEndInteractable extends Interactable {
    public LevelEndInteractable(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, "Press 'E' to end the level", p);
    }

    @Override
    public void interact() {
        super.interact();
        KeyInputs.keysPressed[KeyEvent.VK_E] = false;
        GameManager.nextLevel();
    }
}
