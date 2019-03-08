import java.util.Random;

public class CrushGrid {

    private int[][] grid;

    private CrushController cont;

    CrushGrid(CrushController _cont) {
        cont = _cont;

        grid = new int[8][8];

        Random num = new Random();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = num.nextInt(8);
            }
        }
    }

    void update(int[][] matchesFound) {
        for (int i = 0; i < matchesFound.length; i++) {
            System.out.println("grid[i][0]: " + grid[i][0]);
            int locRow = matchesFound[i][0];
            int locCol = matchesFound[i][1];
            fallDown(locRow, locCol);
        }
        passUpdatedGrid();
    }

    private void fallDown(int row, int col) {
        while (row > 0) {
            grid[row][col] = grid[row - 1][col];
            row--;
            System.out.println("row: " + row + ", col: " + col);
        }
        Random newNum = new Random();
        grid[row][col] = newNum.nextInt(8);
    }

    void passUpdatedGrid() {
        cont.getUpdatedGrid(grid);
    }

    int getValueAt(int row, int col) {
        return grid[row][col];
    }
}
