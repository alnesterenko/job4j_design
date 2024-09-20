package ru.job4j.ood.lsp.parking.parkings;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking implements Parking {
    private List<Auto> parkingForCarsList;
    private final int parkingSpacesForCars;
    private final int parkingSpacesForTrucks;

    public AbstractParking(int parkingSpacesForCars, int parkingSpacesForTrucks) {
        this.parkingSpacesForCars = parkingSpacesForCars;
        this.parkingSpacesForTrucks = parkingSpacesForTrucks;
        this.parkingForCarsList = new ArrayList<>(parkingSpacesForCars);
    }

    /**
     * @return
     */
    @Override
    public int getParkingSpacesForCars() {
        return this.parkingSpacesForCars;
    }

    /**
     * @return
     */
    @Override
    public int getParkingSpacesForTrucks() {
        return this.parkingSpacesForTrucks;
    }

    /**
     * @return
     */
    @Override
    public List<Auto> getParkingCarsList() {
        return this.parkingForCarsList;
    }

    @Override
    public int getFreeSpacesOnCarsParking() {
        return getParkingSpacesForCars() - getParkingCarsList().size();
    }

    @Override
    public int getFreeSpacesOnTrucksParking() {
        return getParkingSpacesForCars() - getParkingCarsList().size();
    }
}
