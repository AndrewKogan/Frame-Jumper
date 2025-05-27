package GridPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitPanel extends JPanel implements ActionListener {
    private JButton submitButton;
    private JButton quitButton;
    private GridPuzzle puzzleReference;

    public SubmitPanel(GridPuzzle g) {
        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);

        quitButton = new JButton("CANCEL");
        quitButton.addActionListener(this);

        add(submitButton);
        add(quitButton);

        puzzleReference = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 200, 70);
        submitButton.setLocation(0, 0);
        quitButton.setLocation(100, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object sender = e.getSource();
        if (sender == submitButton) {
            if (puzzleReference.checkPuzzleSolution())
                puzzleReference.onPuzzleSolved();
            else
                System.out.println("Incorrect");
        }
        else if (sender == quitButton)
            puzzleReference.quitPuzzle();
    }
}
