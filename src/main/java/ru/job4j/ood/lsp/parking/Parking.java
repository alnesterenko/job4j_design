package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean addAuto(Auto auto);
    boolean removeAuto(Auto auto);
    int getFreeSpacesForCars();
    int setFreeSpacesForCars();
    int getFreeSpacesForTrucks();
    int setFreeSpacesForTrucks();
    List<Auto> getParkingCarsList();
    List<Auto> getParkingTrucksList();
    /* Проверять можно ли разметить ещё машины или нет будет класс ParkingControl,
    * поэтому тут никаких методов-проверок нет. */
}
