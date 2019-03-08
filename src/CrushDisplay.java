import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CrushDisplay {

    private CrushController cont;

    private final int BOARD_SIZE = 8;

    CrushDisplay(CrushController _cont) {
        cont = _cont;
        createBoard();
    }

    private void createBoard() {
        JFrame window = new JFrame("");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setMinimumSize(new Dimension(400, 400));
        mainPanel.setPreferredSize(new Dimension(400, 400));
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        createButtons(boardPanel);

        mainPanel.add(boardPanel);
        window.add(mainPanel);

        window.setMinimumSize(new Dimension(400, 400));
        window.setPreferredSize(new Dimension(400, 400));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

    }

    private void createButtons(JPanel pan) {

        CrushButton[][] allButtons = new CrushButton[8][8];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                allButtons[row][col] = new CrushButton(row ,col);
                allButtons[row][col].setText(Integer.toString(cont.getSymbolByLocation(row, col)));
                allButtons[row][col].addActionListener(e -> {
                        CrushButton button = (CrushButton) e.getSource();
                        int xPos = button.getXLoc();
                        int yPos = button.getYLoc();
                        crushButtonClicked(xPos, yPos);
                    });
                pan.add(allButtons[row][col]);
            }
        }
    }

    private void crushButtonClicked(int x, int y) {
        cont.crushButtonClicked(x, y);
    }
}
