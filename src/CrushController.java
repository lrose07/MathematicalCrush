import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class CrushController {

    private CrushGrid grid;
    private CrushDisplay view;

    private ArrayList<int[]> matchesFoundList = new ArrayList<>();
    private int[][] matchesArray;

    CrushController() {
        grid = new CrushGrid(this);
        view = new CrushDisplay(this);
    }

    void crushButtonClicked(int x, int y) {
        matchesFoundList.clear();
        checkForMatches(x, y);
        convertMatchesListToArray();
        sortByX(matchesArray);
        if (matchesArray.length > 1) grid.update(matchesArray);
        if (!grid.isThereAMatchLeft()) {
            noMatches();
        }
    }

    private void checkForMatches(int initRow, int initCol) {
        int[] current = {initRow, initCol};
        if (isNotInListAlready(current)) {
            matchesFoundList.add(current);
            int comparisonSymbol = getSymbolByLocation(initRow, initCol);
            int upRow = initRow == 0 ? -1 : initRow - 1;
            int rightCol = initCol == 7 ? -1 : initCol + 1;
            int downRow = initRow == 7 ? -1 : initRow + 1;
            int leftCol = initCol == 0 ? -1 : initCol - 1;
            if (upRow != -1 && getSymbolByLocation(upRow, initCol) == comparisonSymbol) {
                checkForMatches(upRow, initCol);
            }
            if (rightCol != -1 && getSymbolByLocation(initRow, rightCol) == comparisonSymbol) {
                checkForMatches(initRow, rightCol);
            }
            if (downRow != -1 && getSymbolByLocation(downRow, initCol) == comparisonSymbol) {
                checkForMatches(downRow, initCol);
            }
            if (leftCol != -1 && getSymbolByLocation(initRow, leftCol) == comparisonSymbol) {
                checkForMatches(initRow, leftCol);
            }
        }
    }

    private boolean isNotInListAlready(int[] arr) {
        for (int[] set : matchesFoundList) {
            if ((set[0] == arr[0]) && (set[1] == arr[1])) {
                return false;
            }
        }
        return true;
    }

    private void convertMatchesListToArray() {
        matchesArray = new int[matchesFoundList.size()][2];
        for (int i = 0; i < matchesFoundList.size(); i++) {
            matchesArray[i][0] = matchesFoundList.get(i)[0];
            matchesArray[i][1] = matchesFoundList.get(i)[1];
        }
    }

    private void sortByX(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingDouble(a -> a[0]));
        matchesArray = arr.clone();
    }

    int getSymbolByLocation(int row, int col) {
        return grid.getValueAt(row, col);
    }

    void getUpdatedGrid(int[][] grid) {
        view.updateView(grid);
    }

    private void noMatches() {
        System.out.println("no matches");
    }

    int getScore() {
        return 5000;
    }
}
