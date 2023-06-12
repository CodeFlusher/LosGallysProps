package me.themiggergames.losgallysprops.util;

public class IntegerStatementManager {
    protected int currentStatement;
    protected int maxState;
    protected int minState;

    protected IntegerStatementManager(int minStatement, int maxStatement) {
        maxState = maxStatement;
        minState = minStatement;
        currentStatement = minState;
    }

    public static IntegerStatementManager of(int minStatement, int maxStatement) {
        return new IntegerStatementManager(minStatement, maxStatement);
    }

    public int getCurrentStatement() {
        return currentStatement;
    }

    public void reset() {
        currentStatement = minState;
    }

    public int changeStatement() {
        if (currentStatement < maxState) {
            currentStatement++;
            return currentStatement;
        } else {
            currentStatement = minState;
            return currentStatement;
        }
    }
}
