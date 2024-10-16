package ru.job4j.algo.sort;

import java.util.Arrays;

public class IntervalMerger {

    public int[][] merge(int[][] intervals) {
        int[][] result;
        switch (intervals.length) {
            case 0:
                result = new int[0][0];
                break;
            case 1:
                result = intervals;
                break;
            default:
                Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
                int[][] tempArr = new int[intervals.length][2];
                int counterCellsInResult = 0;
                int startI = intervals[0][0];
                int endI = intervals[0][1];
                for (int i = 1; i < intervals.length; i++) {
                    int tempStartInterval = intervals[i][0];
                    int tempEndtInterval = intervals[i][1];
                    if ((tempStartInterval >= startI && tempStartInterval <= endI)
                            || (tempEndtInterval >= startI && tempEndtInterval <= endI)
                            || (tempStartInterval <= startI && tempEndtInterval >= endI)) {
                        startI = Math.min(startI, tempStartInterval);
                        endI = Math.max(endI, tempEndtInterval);
                    } else {
                        tempArr[counterCellsInResult++] = new int[]{startI, endI};
                        startI = tempStartInterval;
                        endI = tempEndtInterval;
                    }
                    if (i == intervals.length - 1) {
                        startI = Math.min(startI, tempStartInterval);
                        endI = Math.max(endI, tempEndtInterval);
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
