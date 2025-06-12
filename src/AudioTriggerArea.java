import java.awt.*;

public class AudioTriggerArea extends TriggerArea {
    private String audioPath;
    private boolean played = false;

    public AudioTriggerArea(int x, int y, int width, int height, Player p, String audioPath) {
        super(x, y, width, height, p);
        this.audioPath = audioPath;
    }

    @Override
    protected void onTriggerEnter() {
        if (!played) {
            AudioPlayer.playSound(audioPath);
            played = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.drawRect(x, y, width, height);
    }
}
