import Audio.AudioPlayer;

import java.awt.*;

public class AudioTriggerArea extends TriggerArea {
    private String audioPath;
    private boolean played = false;
    private int level;
    int frameCount;
    int numFrameCount;
    boolean alreadyPlayed = false;

    public AudioTriggerArea(int x, int y, int width, int height, Player p, String audioPath, int level, int frameCount) {
        super(x, y, width, height, p);
        this.audioPath = audioPath;
        this.level = level;
        numFrameCount = frameCount;
        this.frameCount = 0;
    }

    @Override
    protected void onTriggerEnter() {
        if (!played && level == GameManager.level) {
            AudioPlayer.playSound(audioPath);
            played = true;
            frameCount = numFrameCount;
        }
    }

    @Override
    public void update(){
        super.update();
        frameCount--;
        if(frameCount>0) playerReference.lockMovement = true;
        if(frameCount<=0 && !alreadyPlayed && played){
            playerReference.lockMovement = false;
            alreadyPlayed = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.drawRect(x, y, width, height);
    }
}
