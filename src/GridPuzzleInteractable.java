import GridPuzzle.GridPuzzle;

public class GridPuzzleInteractable extends Interactable {

    public GridPuzzleInteractable(int x, int y, String imagePath, String tooltip, Player p) {
        super(x, y, imagePath, tooltip, p);
    }

    @Override
    protected void interact() {
        super.interact();
        new GridPuzzle();
    }
}
