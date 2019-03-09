import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CrushDisplay {

    private CrushController cont;

    private CrushButton[][] allButtons;

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
        window.validate();
        window.setVisible(true);

    }

    private void createButtons(JPanel pan) {

        allButtons = new CrushButton[8][8];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                allButtons[row][col] = new CrushButton(row ,col);
                allButtons[row][col].setBackground(setButtonColor(cont.getSymbolByLocation(row, col)));
                allButtons[row][col].setBorder(null);
                allButtons[row][col].setOpaque(true);
                allButtons[row][col].setBorderPainted(false);
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

    void updateView(int[][] grid) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                allButtons[row][col].setBackground(setButtonColor(grid[row][col]));
            }
        }
    }

    Color setButtonColor(int x) {
        Color c;
        switch (x) {
            case 0:
                c = Color.RED;
                break;
            case 1:
                c = Color.BLUE;
                break;
            case 2:
                c = Color.GREEN;
                break;
            case 3:
                c = Color.MAGENTA;
                break;
            case 4:
                c = Color.ORANGE;
                break;
            case 5:
                c = Color.CYAN;
                break;
            case 6:
                c = Color.WHITE;
                break;
            default:
                c = Color.YELLOW;

        }

        return c;
    }
}
