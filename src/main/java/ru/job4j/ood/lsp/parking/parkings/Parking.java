package ru.job4j.ood.lsp.parking.parkings;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

public interface Parking {
    boolean addAuto(Auto auto);
    boolean removeAuto(Auto auto);
    boolean removeAuto(String number);
    int getFreeSpacesForCars();
    void setFreeSpacesForCars(int newFreeSpaces);
    int getFreeSpacesForTrucks();
    void setFreeSpacesForTrucks(int newFreeSpaces);
    List<Auto> getParkingCarsList();
    List<Auto> getParkingTrucksList();
    /* Проверять можно ли разметить ещё машины или нет будет класс ParkingControl,
    * поэтому тут никаких методов-проверок нет. */
}
