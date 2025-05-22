import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInputs implements MouseListener {
    public boolean leftClick, rightClick;
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseCode = e.getButton();
        if(mouseCode == 1){
            leftClick=true;
        }
        if(mouseCode == 3){
            rightClick=true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}