import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation implements ActionListener {
    private ArrayList<BufferedImage> frames;
    private Timer timer;
    private int currentFrame;

    public Animation(ArrayList<BufferedImage> frames) {
        this.frames = frames;
        currentFrame = 0;
        timer = new Timer(1000 / GamePanel.FPS, this);
        timer.start();
    }

    public BufferedImage getActiveFrame() {
        return frames.get(currentFrame);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            currentFrame++;
            if (currentFrame >= frames.size())
                currentFrame = 0;
        }
    }
}
