package ru.job4j.ood.lsp.parking.parkings;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParkingOnTwoArrayLists extends AbstractParking {

    private List<Auto> parkingForTrucksList;

    /**
     * Такой разделённый конструктор нужен чтобы можно было реализовать другой вариант класса-парковки:
     * на одном ArrayList-е
     *
     * @param parkingSpacesForCars   -- размер парковки для легковушек(количество свободных мест)
     * @param parkingSpacesForTrucks -- размер парковки для грузовиков(количество свободных мест)
     */
    public ParkingOnTwoArrayLists(int parkingSpacesForCars, int parkingSpacesForTrucks) {
        super(parkingSpacesForCars, parkingSpacesForTrucks);
        this.parkingForTrucksList = new ArrayList<>(parkingSpacesForTrucks);
    }

    /**
     * @param auto
     * @return
     */
    @Override
    public boolean addAuto(Auto auto) {
        return switch (auto.getClassName()) {
            case "Car" -> addCarToCarsParking(auto);
            case "Truck" -> (addTruckToTrucksParking(auto) || addTruckToCarsParking(auto));
            default -> false;
        };
    }

    private boolean addCarToCarsParking(Auto auto) {
        return checkCarToCarsParking(auto) ? getParkingCarsList().add(auto) : false;
    }

    private boolean checkCarToCarsParking(Auto auto) {
        return getFreeSpacesOnCarsParking() > 0;
    }

    private boolean addTruckToTrucksParking(Auto auto) {
        return checkTruckToTrucksParking(auto) ? getParkingTrucksList().add(auto) : false;
    }

    private boolean checkTruckToTrucksParking(Auto auto) {
        return getFreeSpacesOnTrucksParking() > 0;
    }

    private boolean addTruckToCarsParking(Auto auto) {
        boolean result = checkTruckToCarsParking(auto);
        if (result) {
            int startPosition = getParkingCarsList().size();
            int truckSize = auto.getSize();
            List<Auto> parkingCarsList = getParkingCarsList();
            for (int i = 0; i < truckSize; i++) {
                parkingCarsList.add(startPosition + i, auto);
            }
        }
        return result;
    }

    private boolean checkTruckToCarsParking(Auto auto) {
        return auto.getSize() <= getFreeSpacesOnCarsParking();
    }

    /**
     * @param auto
     * @return
     */
    @Override
    public boolean removeAuto(Auto auto) {
        return getParkingCarsList().removeAll(List.of(auto))
                || getParkingTrucksList().removeAll(List.of(auto));
    }

    /**
     * @param number
     * @return
     */
    public boolean removeAuto(String number) {
        boolean result = false;
        Iterator<Auto> firstListIterator = getParkingCarsList().iterator();
        while (firstListIterator.hasNext()) {
            Auto nextAuto = firstListIterator.next();
            if (number.equals(nextAuto.getNumber())) {
                firstListIterator.remove();
                result = true;
            }
        }
        if (!result) {
            Iterator<Auto> secondListIterator = getParkingTrucksList().iterator();
            while (secondListIterator.hasNext()) {
                Auto nextAuto = secondListIterator.next();
                if (number.equals(nextAuto.getNumber())) {
                    secondListIterator.remove();
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public List<Auto> getParkingCarsList() {
        return super.getParkingCarsList();
    }

    /**
     * @return
     */
    @Override
    public List<Auto> getParkingTrucksList() {
        return this.parkingForTrucksList;
    }

    @Override
    public int getFreeSpacesOnCarsParking() {
        return super.getFreeSpacesOnCarsParking();
    }

    @Override
    public int getFreeSpacesOnTrucksParking() {
        return getParkingSpacesForTrucks() - getParkingTrucksList().size();
    }
}
