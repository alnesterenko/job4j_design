package ru.job4j.ood.lsp.parking.parkings;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.List;

public class ParkingOnTwoArrayLists extends AbstractParking {

    private List<Auto> parkingForTrucksList;

    /**
     * Такой разделённый конструктор нужен чтобы можно было реализовать другой вариант класса-парковки:
     * на одном ArrayList-е
     * @param freeSpacesForCars -- размер парковки для легковушек(количество свободных мест)
     * @param freeSpacesForTrucks -- размер парковки для грузовиков(количество свободных мест)
     */
    public ParkingOnTwoArrayLists(int freeSpacesForCars, int freeSpacesForTrucks) {
        super(freeSpacesForCars, freeSpacesForTrucks);
        this.parkingForTrucksList = new ArrayList<>(freeSpacesForTrucks);
    }

    /**
     * @param auto
     * @return
     */
    @Override
    public boolean addAuto(Auto auto) {
        return false;
    }

    /**
     * @param auto
     * @return
     */
    @Override
    public boolean removeAuto(Auto auto) {
        return false;
    }

    /**
     * @param number
     * @return
     */
    public boolean removeAuto(String number) {
        return false;
    }

    /**
     * @return
     */
    @Override
    public List<Auto> getParkingTrucksList() {
        return this.parkingForTrucksList;
    }

    @Override
    public List<Auto> getParkingCarsList() {
        return super.getParkingCarsList();
    }
}
