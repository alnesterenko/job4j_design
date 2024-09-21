/**
 * Пример взят из моего крайнего домашнего задания.
 * Интерфейс Parking "ориентирован" на работу с двумя раздельными парковками(для легковушек и грузовиков),
 * но реализующий его абстрактный класс AbstractParking "ориентирован" на работу только с парковкой для легковушек,
 * а работу с парковкой для грузовиков он только "имитирует". По-настоящему работать с парковкой для грузовиков будет
 * класс-наследник ParkingOnTwoArrayLists.
 * !!! По-хорошему, интерфейс Parking нужно было разделить на два интерфейса:
 * 1) Работающий с парковкой для легковушек
 * 2) Работающий с парковкой для грузовиков
 */

package ru.job4j.ood.isp;

import ru.job4j.ood.lsp.parking.auto.Auto;

import java.util.ArrayList;
import java.util.Iterator;
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

abstract class AbstractParking implements ru.job4j.ood.isp.Parking {
    private List<Auto> parkingForCarsList;
    private final int parkingSpacesForCars;
    private final int parkingSpacesForTrucks;

    public AbstractParking(int parkingSpacesForCars, int parkingSpacesForTrucks) {
        this.parkingSpacesForCars = parkingSpacesForCars;
        this.parkingSpacesForTrucks = parkingSpacesForTrucks;
        this.parkingForCarsList = new ArrayList<>(parkingSpacesForCars);
    }

    @Override
    public int getParkingSpacesForCars() {
        return this.parkingSpacesForCars;
    }

    @Override
    public int getParkingSpacesForTrucks() {
        return this.parkingSpacesForTrucks;
    }

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

class ParkingOnTwoArrayLists extends ru.job4j.ood.isp.AbstractParking {

    private List<Auto> parkingForTrucksList;

    public ParkingOnTwoArrayLists(int parkingSpacesForCars, int parkingSpacesForTrucks) {
        super(parkingSpacesForCars, parkingSpacesForTrucks);
        this.parkingForTrucksList = new ArrayList<>(parkingSpacesForTrucks);
    }

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

    @Override
    public boolean removeAuto(Auto auto) {
        return getParkingCarsList().removeAll(List.of(auto))
                || getParkingTrucksList().removeAll(List.of(auto));
    }

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

