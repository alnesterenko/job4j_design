package ru.job4j.algo.greedy;

import java.util.ArrayList;
import java.util.List;

public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int startPosition = -1;
        int totalGas = 0, totalCost = 0, tank = 0;
        boolean tail = false;
        List<Integer> listOfStartPositions = findAllStartingPosition(gas, cost);
        for (int oneStartPosition : listOfStartPositions) {
            startPosition = oneStartPosition;
            totalGas = 0;
            totalCost = 0;
            tank = 0;
            tail = false;
            int till = gas.length;
            for (int i = oneStartPosition; i < till; i++) {
                tank += gas[i] - cost[i];
                totalGas += gas[i];
                totalCost += cost[i];
                if (tank < 0) {
                    break;
                }
                if (!tail && i == till - 1) { /* Дошли до края? Продолжаем с начала до стартовой позиции! */
                    tail = true;
                    i = -1;
                    till = oneStartPosition;
                }
            }
            if (totalGas >= totalCost && tank >= 0) { /* Прошли один круг, а бак не стал меньше нуля? Победа! */
                break; /* Другие варианты стартовых позиций проверять не нужно! */
            }
        }
        return totalGas >= totalCost && tank >= 0 ? startPosition : -1;
    }

    private List<Integer> findAllStartingPosition(int[] gas, int[] cost) {
        List<Integer> resultList = new ArrayList<>();
        int lengthOfArrays = gas.length;
        if (lengthOfArrays == cost.length) {
            for (int i = 0; i < lengthOfArrays; i++) {
                if (gas[i] > cost[i]) {
                    resultList.add(i);
                }
            }
        }
        return resultList;
    }
}
