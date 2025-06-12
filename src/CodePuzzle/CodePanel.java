package CodePuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Audio.AudioPlayer;

public class CodePanel extends JPanel implements ActionListener {
    private JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private JButton submitButton, cancelButton;

    private String attempt = "";

    private CodePuzzle puzzleReference;

    public CodePanel(CodePuzzle puzzle) {
        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");

        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");

        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        add(button0);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        add(button7);
        add(button8);
        add(button9);
        add(submitButton);
        add(cancelButton);

        puzzleReference = puzzle;

        setPreferredSize(new Dimension(400, 500));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        button1.setLocation(100, 100);
        button2.setLocation(200, 100);
        button3.setLocation(300, 100);
        button4.setLocation(100, 200);
        button5.setLocation(200, 200);
        button6.setLocation(300, 200);
        button7.setLocation(100, 300);
        button8.setLocation(200, 300);
        button9.setLocation(300, 300);
        button0.setLocation(200, 400);

        submitButton.setLocation(50, 450);
        cancelButton.setLocation(300, 450);

        g.setFont(new Font("Digital-7", Font.PLAIN, 36));
        if (!attempt.isEmpty()) g.drawString(attempt.charAt(0) + "", 100, 50);
        if (attempt.length() >= 2) g.drawString(attempt.charAt(1) + "", 175, 50);
        if (attempt.length() >= 3) g.drawString(attempt.charAt(2) + "", 250, 50);
        if (attempt.length() >= 4) g.drawString(attempt.charAt(3) + "", 325, 50);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (attempt.length() < 4) {
            if (source == button0) attempt += "0";
            else if (source == button1) attempt += "1";
            else if (source == button2) attempt += "2";
            else if (source == button3) attempt += "3";
            else if (source == button4) attempt += "4";
            else if (source == button5) attempt += "5";
            else if (source == button6) attempt += "6";
            else if (source == button7) attempt += "7";
            else if (source == button8) attempt += "8";
            else if (source == button9) attempt += "9";
            if (source != cancelButton) AudioPlayer.playSound("src\\Audio\\ButtonPress.wav");
        }

        if (source == submitButton && attempt.length() == 4) {
            if (puzzleReference.checkPuzzleSolution(attempt)) puzzleReference.onPuzzleSolved();
            else AudioPlayer.playSound("src\\Audio\\Incorrect.wav");
            attempt = "";
        }
        else if (source == cancelButton)
            puzzleReference.onQuit();

        repaint();
    }
}
