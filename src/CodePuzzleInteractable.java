import CodePuzzle.CodePuzzle;

import java.awt.event.KeyEvent;

public class CodePuzzleInteractable extends Interactable {
    int usage = 0;

    public CodePuzzleInteractable(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, p);
    }

    @Override
    protected void interact() {
        usage = CodePuzzle.usage;
        if(usage<1 && KeyInputs.keysPressed[KeyEvent.VK_E]) {
            usage++;
            super.interact();
            new CodePuzzle(usage);
            for(int i = 0; i < 200; i++) KeyInputs.keysPressed[i] = false;
        }
    }
}
