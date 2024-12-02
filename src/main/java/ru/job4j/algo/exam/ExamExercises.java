package ru.job4j.algo.exam;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.newcoll.tree.Node;

import java.util.*;
import java.util.stream.Collectors;

public class ExamExercises {

    /**
     * Метод возвращает индекс первого повторяющегося числа.
     * Теперь временная сложность O(n).
     * @param array с набором чисел или пустой.
     * @return индекс первого числа которое повторяется или -1 если переданный массив пустой или число не повторяется.
     */
    public static int findIndexFirstRepetition(int[] array) {
        int indexOfFirstRepetion = -1;
        int repeatElement = 0;
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < array.length; i++) {
            if (!set.add(array[i])) {
                repeatElement = array[i];
                break;

            }
        }
        if (repeatElement != 0) {
            for (Integer oneInt : set) {
                indexOfFirstRepetion++;
                if (oneInt.equals(repeatElement)) {
                    break;
                }
            }
        }
        return indexOfFirstRepetion;
    }

    /**
     * Метод проверяющий все комнаты.
     * @param building
     * @return true -- если получится открыть все комнаты, иначе false.
     */
    public static boolean checkAllRooms(int[][] building) {
        boolean result = false;
        int countOfRooms = building.length;
        int countOfVisitedRooms = 0;
        if (countOfRooms > 0) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            while (!queue.isEmpty()) {
                countOfVisitedRooms++;
                if (countOfRooms == countOfVisitedRooms) {
                    result = true;
                    break;
                }
                int tempKey = queue.remove();
                List<Integer> tempList = checkNextRoom(building[tempKey]);
                for (var oneKey : tempList) {
                    queue.add(oneKey);
                }
            }
        }
        return result;
    }

    private static List<Integer> checkNextRoom(int[] arr) {
        return Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList());
    }
}
