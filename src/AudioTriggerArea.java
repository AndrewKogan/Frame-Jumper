import Audio.AudioPlayer;

import java.awt.*;

public class AudioTriggerArea extends TriggerArea {
    private String audioPath;
    private boolean played = false;
    private int level;

    public AudioTriggerArea(int x, int y, int width, int height, Player p, String audioPath, int level) {
        super(x, y, width, height, p);
        this.audioPath = audioPath;
        this.level = level;
    }

    @Override
    protected void onTriggerEnter() {
        if (!played && level == GameManager.level) {
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
