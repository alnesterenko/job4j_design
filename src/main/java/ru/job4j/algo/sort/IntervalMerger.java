package ru.job4j.algo.sort;

public class IntervalMerger {

    public int[][] merge(int[][] intervals) {
        int[][] result;
        if (intervals.length == 0) {
            result = new int[0][0];
        } else {
            int[][] tempArr = new int[intervals.length][2];
            int counterCellsInResult = 0;
            int startI = intervals[0][0];
            int endI = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int tempStartInterval = intervals[i][0];
                int tempEndtInterval = intervals[i][1];
                if ((tempStartInterval >= startI && tempStartInterval <= endI)
                        || (tempEndtInterval >= startI && tempEndtInterval <= endI)) {
                    startI = Math.min(startI, tempStartInterval);
                    endI = Math.max(endI, tempEndtInterval);
                } else {
                    tempArr[counterCellsInResult++] = new int[]{startI, endI};
                    startI = tempStartInterval;
                    endI = tempEndtInterval;
                }
                if (i == intervals.length - 1) {
                    tempArr[counterCellsInResult++] = new int[]{startI, endI};
                }
            }
            result = new int[counterCellsInResult][2];
            for (int i = 0; i < counterCellsInResult; i++) {
                result[i] = tempArr[i];
            }
        }
        return result;
    }
}
