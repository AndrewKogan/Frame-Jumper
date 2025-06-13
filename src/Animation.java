import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Animation implements ActionListener {
    private ArrayList<BufferedImage> frames;
    private Timer timer;
    public int currentFrame;

    private boolean loop;
    public boolean finished;

    public Animation(String folderPath, int frameCount, int frameRate, boolean loop) {
        frames = new ArrayList<>();
        for (int i = 1; i <= frameCount; i++) {
            try {
                frames.add(ImageIO.read(new File(folderPath + "\\00" + i + ".png")));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        currentFrame = 0;
        timer = new Timer(1000 / frameRate, this);

        this.loop = loop;
    }

    public BufferedImage getActiveFrame() {
        return frames.get(currentFrame);
    }

    public BufferedImage getFirstFrame() {
        return frames.getFirst();
    }

    public BufferedImage getLastFrame() {
        return frames.getLast();
    }

    public void play() {
        currentFrame = 0;
        finished = false;
        timer.restart();
    }

    public void stop() {
        timer.stop();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            currentFrame++;
            if (currentFrame >= frames.size()) {
                if (!loop) {
                    timer.stop();
                    finished = true;
                    currentFrame = frames.size() - 1;
                }
                else
                    currentFrame = 0;
            }
        }
    }
}
