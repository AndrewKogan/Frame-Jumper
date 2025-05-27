import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class Cutscene implements ActionListener {
    private String url;
    private Timer timer;
    private GamePanel gamePanel;

    public Cutscene(String url, int length, GamePanel g) {
        this.url = url;
        gamePanel = g;
        timer = new Timer(length, this);
    }

    public void play() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI oURL = new URI(url);
            desktop.browse(oURL);
            timer.start();
            gamePanel.setPanelToDisplay(Panel.CUTSCENE_PLAYING);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == timer) {
            timer.stop();
            gamePanel.setPanelToDisplay(Panel.CUTSCENE_ENDED);
        }
    }
}
