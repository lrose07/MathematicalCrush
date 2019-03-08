import javax.swing.*;

class CrushButton extends JButton {

    private int xLoc;
    private int yLoc;

    CrushButton(int _x, int _y) {
        super();
        xLoc = _x;
        yLoc = _y;
    }

    int getXLoc() {
        return xLoc;
    }

    int getYLoc() {
        return yLoc;
    }
}
