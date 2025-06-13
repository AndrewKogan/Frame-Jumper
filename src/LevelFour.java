import Audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class LevelFour extends GamePanel {
    private Boss boss;

    private Cutscene introCutscene;
    private Cutscene postCutscene;

    private Timer cutsceneEndTimer = new Timer(35000, this);

    public LevelFour() {
        super();
        this.setPreferredSize(new Dimension(1500, 1000));

        player.x = 750;
        player.y = 800;
        boss = new Boss(player);

        introCutscene = new Cutscene("https://youtube.com/shorts/O2MkW4CmCjc?feature=share", 48000, this);
        postCutscene = new Cutscene("https://youtube.com/shorts/iFKPYk05D-g?feature=share", 100000, this);
    }

    @Override
    public void startGameThread() {
        super.startGameThread();
        introCutscene.play();
    }

    @Override
    public void update() {
        player.update();
        boss.update();

        if (boss.health <= 0)  {
            AudioPlayer.stopMusic();
            postCutscene.play();
            cutsceneEndTimer.start();
        }

        for (Wall wall : walls) wall.update();
    }

    @Override
    public void paintLevel(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for (Wall wall : walls) wall.draw(g2);
        boss.draw(g2);
        player.draw(g2);
    }

    @Override
    public void makeWalls() {
        walls.add(new Wall(0,900,1500,1000,player,false));
        walls.add(new Wall(-950, -200, 1000, 2000, player, false));
        walls.add(new Wall(1450, -200, 1000, 2000, player, false));
    }

    @Override
    public void reset() {
        super.reset();
        player.x = 750;
        player.y = 800;
        boss = new Boss(player);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object source = e.getSource();
        if (source == cutsceneEndTimer) closeGame();
        if (e.getSource() == cutsceneContinueButton) Audio.AudioPlayer.playMusic("src\\Audio\\Roar of Dedede - Kirby and the Forgotten Land OST [050]-[AudioTrimmer.com].wav");
    }

    private void closeGame() {
        try {
            Robot robot = new Robot();
            Point mouseStartPos = MouseInfo.getPointerInfo().getLocation();
            for (int i = 1; i <= 100; i++) {
                robot.mouseMove(mouseStartPos.x + (925 - mouseStartPos.x) * i / 100, mouseStartPos.y + (1080 - mouseStartPos.y) * i / 100);
                robot.delay(5);
            }
            robot.mouseMove(925,1080);
            robot.mousePress(InputEvent.getMaskForButton(MouseEvent.BUTTON1));
            robot.mouseRelease(InputEvent.getMaskForButton(MouseEvent.BUTTON1));

            mouseStartPos = MouseInfo.getPointerInfo().getLocation();
            for (int i = 1; i <= 100; i++) {
                robot.mouseMove(mouseStartPos.x + (1325 - mouseStartPos.x) * i / 100, mouseStartPos.y + (25 - mouseStartPos.y) * i / 100);
                robot.delay(5);
            }
            robot.mouseMove(1325,25);
            robot.mousePress(InputEvent.getMaskForButton(MouseEvent.BUTTON1));
            robot.mouseRelease(InputEvent.getMaskForButton(MouseEvent.BUTTON1));
        } catch (AWTException e) {
            System.out.println(e.getMessage());
        }

    }
}
