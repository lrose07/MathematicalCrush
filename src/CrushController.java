import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class CrushController {

    private CrushGrid grid;
    private CrushDisplay view;

    private ArrayList<int[]> matchesFoundList = new ArrayList<>();
    private int[][] matchesArray;

    int recurseCount = 0;

    CrushController() {
        grid = new CrushGrid(this);
        view = new CrushDisplay(this);
    }

    void crushButtonClicked(int x, int y) {
        matchesFoundList.clear();
        recurseCount = 0;
        checkForMatches(x, y);
        convertMatchesListToArray();
        System.out.println("matchesArray in controller: " + matchesArray.length);
        sortByY(matchesArray);
        for (int[] out : matchesArray) {
            for (int in : out) {
                System.out.println(in);
            }
        }
        if (matchesArray.length > 1) grid.update(matchesArray);
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
        System.out.println("matchesFoundList: " + matchesFoundList.size());
        matchesArray = new int[matchesFoundList.size()][2];
        for (int i = 0; i < matchesFoundList.size(); i++) {
            matchesArray[i][0] = matchesFoundList.get(i)[0];
            matchesArray[i][1] = matchesFoundList.get(i)[1];
        }
    }

    private void sortByY(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingDouble(a -> a[1]));
        matchesArray = arr.clone();
    }

    int getSymbolByLocation(int row, int col) {
        return grid.getValueAt(row, col);
    }

    void getUpdatedGrid(int[][] grid) {
        view.updateView(grid);
    }
}
