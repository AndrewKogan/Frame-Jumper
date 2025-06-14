import Audio.AudioPlayer;
import CodePuzzle.CodePuzzle;

import java.awt.event.KeyEvent;

public class CodePuzzleInteractable extends Interactable {
    private CodePuzzle puzzleReference;

    public CodePuzzleInteractable(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, p);
    }

    @Override
    public void update() {
        super.update();
        if (puzzleReference != null && !puzzleReference.getWindow().isVisible()) puzzleReference = null;
    }

    @Override
    protected void interact() {
        if (puzzleReference == null) {
            super.interact();
            AudioPlayer.playSound("src\\Audio\\CodePuzzleIntro.wav");
            puzzleReference = new CodePuzzle();
            for(int i = 0; i < 200; i++) KeyInputs.keysPressed[i] = false;
        }
    }
}
