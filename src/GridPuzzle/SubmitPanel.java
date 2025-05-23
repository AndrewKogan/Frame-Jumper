package GridPuzzle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitPanel extends JPanel implements ActionListener {
    private JButton submitButton;
    private GridPuzzle puzzleReference;

    public SubmitPanel(GridPuzzle g) {
        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);

        add(submitButton);

        puzzleReference = g;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        submitButton.setLocation(0, 0);
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
    }
}
