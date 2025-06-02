public class Spikes extends TriggerArea {
    public Spikes(int x, int y, Player p) {
        super(x, y, "src\\images\\spikes.png", p);
    }

    @Override
    protected void onTriggerEnter() {
        playerReference.panel.reset();
    }
}
