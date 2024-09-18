package ru.job4j.ood.lsp.parking.parkings;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParking implements Parking {
    private List<Auto> parkingForCarsList;
    private int freeSpacesForCars;
    private int freeSpacesForTrucks;

    public AbstractParking(int freeSpacesForCars, int freeSpacesForTrucks) {
        this.freeSpacesForCars = freeSpacesForCars;
        this.freeSpacesForTrucks = freeSpacesForTrucks;
        this.parkingForCarsList = new ArrayList<>(freeSpacesForCars);
    }

    /**
     * @return
     */
    @Override
    public int getFreeSpacesForCars() {
        return this.freeSpacesForCars;
    }

    /**
     * @return
     */
    @Override
    public void setFreeSpacesForCars(int newFreeSpaces) {
        this.freeSpacesForCars = newFreeSpaces;
    }

    /**
     * @return
     */
    @Override
    public int getFreeSpacesForTrucks() {
        return this.freeSpacesForTrucks;
    }

    /**
     * @return
     */
    @Override
    public void setFreeSpacesForTrucks(int newFreeSpaces) {
        this.freeSpacesForTrucks = newFreeSpaces;
    }

    /**
     * @return
     */
    @Override
    public List<Auto> getParkingCarsList() {
        return this.parkingForCarsList;
    }
}
