package GridPuzzle;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private Color color;

    public GridPanel(Color c) {
        color = c;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, 200, 200);
    }
}
