package ru.job4j.algo.sort;

import java.util.Arrays;

public class Quick {
    public static void quickSort(int[] sequence) {
        quickSort(sequence, 0, sequence.length - 1);
    }

    private static void quickSort(int[] sequence, int start, int end) {
        if (start >= end) {
            return;
        }
        int h = breakPartition(sequence, start, end);
        quickSort(sequence, start, h - 1);
        quickSort(sequence, h + 1, end);
    }

    private static int breakPartition(int[] sequence, int start, int end) {
        int leftIndex = start + 1;
        int supportElement = sequence[start];
        int rightIndex = end;
        while (true) {
            while (leftIndex < end && sequence[leftIndex] < supportElement) {
                leftIndex++;
            }
            while (sequence[rightIndex] > supportElement) {
                rightIndex--;
            }
            if (leftIndex >= rightIndex) {
                break;
            }
            swap(sequence, leftIndex++, rightIndex--);
        }
        swap(sequence, start, rightIndex);
        return rightIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {0, 5, -2, 7, 3, -2};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
