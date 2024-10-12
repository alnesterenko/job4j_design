package ru.job4j.algo;

import java.util.Arrays;

/* Временная эффективность алгоритма O(n^2) */

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int[] resultArray = null;
        int[] tempArray = new int[k];
        int resultStart = 0;
        int cursor = resultStart;
        int counter = 0;
        int numsSize = nums.length;
        while (resultStart + (k - 1) < numsSize) {
                if (containsInTempArray(tempArray, nums[cursor])) {
                    tempArray = new int[k];
                    counter = 0;
                    resultStart++;
                    cursor = resultStart - 1;
                } else {
                    tempArray[counter] = nums[cursor];
                    counter++;
                }
            if (counter == k) {
                resultArray = new int[]{resultStart, resultStart + (k - 1)};
                break;
            }
            cursor++;
        }
        return resultArray;
    }

    private static boolean containsInTempArray(int[] tempArray, int oneDigit) {
        boolean result = false;
        int tempArraySize = tempArray.length;
        int i = 0;
        while (i < tempArraySize) {
            if (tempArray[i] == oneDigit) {
                result = true;
                break;
            }
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}