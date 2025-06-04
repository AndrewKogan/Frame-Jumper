import GridPuzzle.GridPuzzle;

public class GridPuzzleInteractable extends Interactable {

    public GridPuzzleInteractable(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, p);
    }

    @Override
    protected void interact() {
        super.interact();
        new GridPuzzle();
    }
}
