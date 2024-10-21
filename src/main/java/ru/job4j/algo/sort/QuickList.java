package ru.job4j.algo.sort;

import java.util.Comparator;
import java.util.List;

public class QuickList {

    public static <T> void quickSort(List<T> sequence, Comparator<T> comparator) {
        quickSort(sequence, 0, sequence.size() - 1, comparator);
    }

    private static <T> void quickSort(List<T> sequence, int start, int end, Comparator<T> comparator) {
        if (start >= end) {
            return;
        }
        int h = breakPartition(sequence, start, end, comparator);
        quickSort(sequence, start, h - 1, comparator);
        quickSort(sequence, h + 1, end, comparator);

    }

    private static <T> int breakPartition(List<T> sequence, int start, int end, Comparator<T> comparator) {
        int leftIndex = start + 1;
        T supportElement = sequence.get(start);
        int rightIndex = end;
        while (true) {
            while (leftIndex < end && comparator.compare(sequence.get(leftIndex), supportElement) < 0) {
                leftIndex++;
            }
            while (comparator.compare(sequence.get(rightIndex), supportElement) > 0) {
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

    private static <T> void swap(List<T> array, int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}
