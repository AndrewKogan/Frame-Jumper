public class Lever extends Interactable {
    private Door door;

    public Lever(int x, int y, String imagePath, Player p, Door d) {
        super(x, y, imagePath, p);
        door = d;
    }

    @Override
    protected void interact() {
        super.interact();
        door.open();
    }
}
