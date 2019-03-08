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

    int getValueAt(int row, int col) {
        return grid[row][col];
    }
}