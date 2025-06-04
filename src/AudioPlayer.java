import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {
    private static Clip music;

    private AudioPlayer() {}

    public static void playSound(String audioPath) {
        File audioFile = new File(audioPath);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void playMusic(String musicPath) {
        File musicFile = new File(musicPath);
        try {
            if (music != null)
                music.stop();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            music = clip;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
