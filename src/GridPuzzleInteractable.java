import GridPuzzle.GridPuzzle;

import java.awt.event.KeyEvent;

public class GridPuzzleInteractable extends Interactable {

    public GridPuzzleInteractable(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, p);
    }

    @Override
    protected void interact() {
        super.interact();
        if(!GridPuzzle.opened && !GridPuzzle.solved){
            new GridPuzzle();
            KeyInputs.keysPressed[KeyEvent.VK_E] = false;
        }
    }
}
