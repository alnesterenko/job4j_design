package ru.job4j.ood.lsp.parking.parkings;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.List;

public interface Parking {
    boolean addAuto(Auto auto);
    boolean removeAuto(Auto auto);
    boolean removeAuto(String number);
    int getParkingSpacesForCars();
    int getParkingSpacesForTrucks();
    List<Auto> getParkingCarsList();
    List<Auto> getParkingTrucksList();
    int getFreeSpacesOnCarsParking();
    int getFreeSpacesOnTrucksParking();
}
