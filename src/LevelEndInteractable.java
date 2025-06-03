public class LevelEndInteractable extends Interactable {
    public LevelEndInteractable(int x, int y, String imagePath, String tooltip, Player p) {
        super(x, y, imagePath, tooltip, p);
    }

    @Override
    public void interact() {
        GameManager.nextLevel();
        interactCooldown = 20;
    }
}
