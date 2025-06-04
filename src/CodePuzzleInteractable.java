import CodePuzzle.CodePuzzle;

public class CodePuzzleInteractable extends Interactable {

    public CodePuzzleInteractable(int x, int y, String imagePath, String tooltip, Player p) {
        super(x, y, imagePath, tooltip, p);
    }

    @Override
    protected void interact() {
        super.interact();
        new CodePuzzle();
    }
}
