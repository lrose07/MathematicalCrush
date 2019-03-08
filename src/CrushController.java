import java.util.ArrayList;
import java.util.Stack;

class CrushController {

    CrushGrid grid;

    private boolean foundMatch = false;
    private ArrayList<int[]> matchesFound = new ArrayList<>();
    private Stack<int[]> matchStack = new Stack<>();

    CrushController() {
        grid = new CrushGrid(this);
        new CrushDisplay(this);
    }

    void crushButtonClicked(int x, int y) {
        checkForMatches(x, y);
        for (int i = 0; i < matchesFound.size(); i++) {
            System.out.println(matchesFound.get(i).toString());
        }
    }

    private void checkForMatches(int initX, int initY) {
        // check 4 around. if match, add to stack. once all four checked,
        // pop the top of the stack, add popped to AL, and recurse
        int[] current = {initX, initY};
        matchesFound.add(current);
        int comparisonSymbol = getSymbolByLocation(initX, initY);
        int leftX = initX == 0 ? -1 : initX - 1;
        int rightX = initX == 7 ? -1 : initX + 1;
        int upY = initY == 0 ? -1 : initY - 1;
        int downY = initY == 7 ? -1 : initY + 1;
        //while (foundMatch) {
            if (leftX != -1 && getSymbolByLocation(leftX, initY) == comparisonSymbol) {
                int[] tempL = {leftX, initY};
                matchStack.push(tempL);
                if (!isInListAlready(tempL)) checkForMatches(leftX, initY);
            }
            if (upY != -1 && getSymbolByLocation(initX, upY) == comparisonSymbol) {
                int[] tempU = {initX, upY};
                matchStack.push(tempU);
                if (!isInListAlready(tempU)) checkForMatches(initX, upY);
            }
            if (rightX != -1 && getSymbolByLocation(rightX, initY) == comparisonSymbol) {
                int[] tempR = {rightX, initY};
                matchStack.push(tempR);
                if (!isInListAlready(tempR)) checkForMatches(rightX, initY);
            }
            if (downY != -1 && getSymbolByLocation(initX, downY) == comparisonSymbol) {
                int[] tempD = {initX, downY};
                matchStack.push(tempD);
                if (!isInListAlready(tempD)) checkForMatches(initX, downY);
            }
        //}
    }

    private boolean isInListAlready(int[] arr) {
        for (int i = 0; i < matchesFound.size(); i++) {
            if (matchesFound.get(i)[0] == arr[0] && matchesFound.get(i)[1] == arr[1]) {
                System.out.println("match");
                return true;
            }
        }
        return false;
    }

    int getSymbolByLocation(int row, int col) {
        return grid.getValueAt(row, col);
    }
}
