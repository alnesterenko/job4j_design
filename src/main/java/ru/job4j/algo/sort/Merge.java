package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int[] result = new int[leftLength + rightLength];
        int indexLeft = 0;
        int indexRight = 0;
        int resultArrLength = result.length;
        for (int i = 0; i < resultArrLength; i++) {
            if (indexLeft >= leftLength) {
                System.arraycopy(right, indexRight, result, i, (rightLength) - indexRight);
                break;
            } else if (indexRight >= rightLength) {
                System.arraycopy(left, indexLeft, result, i, (leftLength) - indexLeft);
                break;
            } else {
                result[i] = left[indexLeft] < right[indexRight] ? left[indexLeft++] : right[indexRight++];
            }
        }
        return result;
    }
}
