public class Spikes extends TriggerArea {
    public Spikes(int x, int y, String imagePath, Player p) {
        super(x, y, imagePath, p);
    }

    @Override
    protected void onTriggerEnter() {
        playerReference.die();
    }
}