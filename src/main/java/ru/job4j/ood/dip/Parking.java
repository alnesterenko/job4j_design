package ru.job4j.ood.dip;

import java.util.ArrayList;

/**
 * Класс Parking содержит в поле placesForAuto конкретную реализацию списка -- new ArrayList().
 * Это тоже нарушение принцыпа PID.
 * Чтобы этого нарушения небыло, нужно:
 * 1) Изменить ArrayList<Place> placesForAuto на List<Place> placesForAuto.
 * 2) Добавить классу Parking конструктор, в котором он будет принимать ту или иную реализацию списка(List-a).
 */

public class Parking {
    private int placesForCars;
    private int placesForTrucks;
    ArrayList<Place> placesForAuto = new ArrayList();
}

interface Place {
    public static int STANDARD_SIZE = 1;

    int getStandardSize();
}
