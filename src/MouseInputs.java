import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputs implements MouseListener {
    public static boolean leftClick, rightClick;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseCode = e.getButton();
        if (mouseCode == 1){
            leftClick = true;
        }
        if (mouseCode == 3){
            rightClick = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseCode = e.getButton();
        if (mouseCode == 1){
            leftClick = false;
        }
        if (mouseCode == 3){
            rightClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}