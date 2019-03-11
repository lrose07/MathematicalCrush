import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

class CrushDisplay {

    private CrushController cont;

    private JFrame window;
    private CrushButton[][] allButtons;
    private JLabel score;

    private final int BOARD_SIZE = 8;

    CrushDisplay(CrushController _cont) {
        cont = _cont;
        createBoard();
    }

    private void createBoard() {
        window = new JFrame("");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setMinimumSize(new Dimension(400, 500));
        mainPanel.setPreferredSize(new Dimension(400, 500));
        JPanel boardPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        boardPanel.setMinimumSize(new Dimension(400, 400));
        boardPanel.setPreferredSize(new Dimension(400, 400));
        JPanel scorePanel = new JPanel(new FlowLayout());
        scorePanel.setMinimumSize(new Dimension(400, 100));
        scorePanel.setPreferredSize(new Dimension(400, 100));

        score = new JLabel("Score: " + cont.getScore());
        scorePanel.add(score);

        createButtons(boardPanel);

        mainPanel.add(boardPanel);
        mainPanel.add(scorePanel);
        window.add(mainPanel);

        window.setMinimumSize(new Dimension(400, 500));
        window.setPreferredSize(new Dimension(400, 500));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.validate();
        window.setVisible(true);

    }

    private void createButtons(JPanel pan) {

        allButtons = new CrushButton[8][8];

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                allButtons[row][col] = new CrushButton(row ,col);
                allButtons[row][col].setBackground(getButtonColor(cont.getSymbolByLocation(row, col)));
                allButtons[row][col].setBorder(null);
                allButtons[row][col].setOpaque(true);
                allButtons[row][col].setBorderPainted(true);
                allButtons[row][col].setBorder(new LineBorder(Color.BLACK));
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
                allButtons[row][col].setBackground(getButtonColor(grid[row][col]));
            }
        }
        score.setText("Score: " + cont.getScore());
        window.repaint();
    }

    private Color getButtonColor(int x) {
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
