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
    }

    private void checkForMatches(int initX, int initY) {
        // check 4 around. if match, add to stack. once all four checked,
        // pop the top of the stack, add popped to AL, and recurse
        int comparisonSymbol = getSymbolByLocation(initX, initY);
        int leftX = initX == 0 ? -1 : initX - 1;
        int rightX = initX == 7 ? -1 : initX + 1;
        int upY = initY == 0 ? -1 : initY - 1;
        int downY = initY == 7 ? -1 : initY + 1;
        while (foundMatch) {
            if (leftX != -1 && getSymbolByLocation(leftX, initY) == comparisonSymbol) {
                int[] temp = {leftX, initY};
                matchStack.push(temp);
                foundMatch = true;
            }
            if (upY != -1 && getSymbolByLocation(initX, upY) == comparisonSymbol) {
                int[] temp = {initX, upY};
                matchStack.push(temp);
                foundMatch = true;
            }
            if (rightX != -1 && getSymbolByLocation(rightX, initY) == comparisonSymbol) {
                int[] temp = {rightX, initY};
                matchStack.push(temp);
                foundMatch = true;
            }
            if (downY != -1 && getSymbolByLocation(initX, downY) == comparisonSymbol) {
                int[] temp = {initX, downY};
                matchStack.push(temp);
                foundMatch = true;
            }
        }
    }

    int getSymbolByLocation(int row, int col) {
        return grid.getValueAt(row, col);
    }
}
