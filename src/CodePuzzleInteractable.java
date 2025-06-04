import CodePuzzle.CodePuzzle;

public class CodePuzzleInteractable extends Interactable {

    public CodePuzzleInteractable(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, p);
    }

    @Override
    protected void interact() {
        super.interact();
        new CodePuzzle();
    }
}
