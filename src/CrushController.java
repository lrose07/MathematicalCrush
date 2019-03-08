import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class CrushController {

    private CrushGrid grid;

    private ArrayList<int[]> matchesFoundList = new ArrayList<>();
    private int[][] matchesArray;

    CrushController() {
        grid = new CrushGrid(this);
        new CrushDisplay(this);
    }

    void crushButtonClicked(int x, int y) {
        checkForMatches(x, y);
        convertMatchesListToArray();
        sortByY(matchesArray);
        grid.update(matchesArray);
    }

    private void checkForMatches(int initX, int initY) {
        int[] current = {initX, initY};
        matchesFoundList.add(current);
        int comparisonSymbol = getSymbolByLocation(initX, initY);
        int leftX = initX == 0 ? -1 : initX - 1;
        int rightX = initX == 7 ? -1 : initX + 1;
        int upY = initY == 0 ? -1 : initY - 1;
        int downY = initY == 7 ? -1 : initY + 1;
        if (leftX != -1 && getSymbolByLocation(leftX, initY) == comparisonSymbol) {
            int[] tempL = {leftX, initY};
            if (isNotInListAlready(tempL)) checkForMatches(leftX, initY);
        }
        if (upY != -1 && getSymbolByLocation(initX, upY) == comparisonSymbol) {
            int[] tempU = {initX, upY};
            if (isNotInListAlready(tempU)) checkForMatches(initX, upY);
        }
        if (rightX != -1 && getSymbolByLocation(rightX, initY) == comparisonSymbol) {
            int[] tempR = {rightX, initY};
            if (isNotInListAlready(tempR)) checkForMatches(rightX, initY);
        }
        if (downY != -1 && getSymbolByLocation(initX, downY) == comparisonSymbol) {
            int[] tempD = {initX, downY};
            if (isNotInListAlready(tempD)) checkForMatches(initX, downY);
        }
    }

    private boolean isNotInListAlready(int[] arr) {
        for (int[] set : matchesFoundList) {
            if ((set[0] != arr[0]) || (set[1] != arr[1])) {
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

    void sortByY(int[][] arr) {
        Arrays.sort(arr, Comparator.comparingDouble(a -> a[1]));
        matchesArray = arr;
    }

    int getSymbolByLocation(int row, int col) {
        return grid.getValueAt(row, col);
    }
}
