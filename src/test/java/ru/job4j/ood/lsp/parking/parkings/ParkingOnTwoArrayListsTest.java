package ru.job4j.ood.lsp.parking.parkings;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.auto.Car;
import ru.job4j.ood.lsp.parking.auto.Truck;

import static org.assertj.core.api.Assertions.*;

class ParkingOnTwoArrayListsTest {

    @Disabled
    @Test
    void  whenAddOneTruck() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        Auto truck = new Truck();
        String firstTruckNumber = truck.getNumber();
        firstParking.addAuto(truck);
        assertThat(firstParking.getFreeSpacesForTrucks()).isEqualTo(4);
        assertThat(firstParking.getParkingTrucksList().get(0).getNumber()).isEqualTo(firstTruckNumber);
        assertThat(firstParking.getFreeSpacesForCars()).isEqualTo(5);
    }

    @Disabled
    @Test
    void  whenAddTwoTrucksAndRemoveBoth() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        Auto truck1 = new Truck();
        Auto truck2 = new Truck();
        String firstTruckNumber = truck1.getNumber();
        String secondTruckNumber = truck2.getNumber();
        firstParking.addAuto(truck1);
        firstParking.addAuto(truck2);
        assertThat(firstParking.getFreeSpacesForTrucks()).isEqualTo(3);
        assertThat(firstParking.getParkingTrucksList().get(0).getNumber()).isEqualTo(firstTruckNumber);
        assertThat(firstParking.getParkingTrucksList().get(1).getNumber()).isEqualTo(secondTruckNumber);
        assertThat(firstParking.getFreeSpacesForCars()).isEqualTo(5);
        assertThat(firstParking.removeAuto(truck1)).isTrue();
        assertThat(firstParking.getFreeSpacesForTrucks()).isEqualTo(4);
        assertThat(firstParking.removeAuto(secondTruckNumber)).isTrue();
        assertThat(firstParking.removeAuto(secondTruckNumber)).isFalse();
        assertThat(firstParking.getFreeSpacesForTrucks()).isEqualTo(5);
    }

    @Disabled
    @Test
    void  whenAddOneCar() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        Auto car = new Car();
        String firstCarNumber = car.getNumber();
        firstParking.addAuto(car);
        assertThat(firstParking.getFreeSpacesForTrucks()).isEqualTo(5);
        assertThat(firstParking.getParkingTrucksList().get(0).getNumber()).isEqualTo(firstCarNumber);
        assertThat(firstParking.getFreeSpacesForCars()).isEqualTo(4);
    }

    @Disabled
    @Test
    void  whenAddTwoCarsAndRemoveBoth() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        Auto car1 = new Car();
        Auto car2 = new Car();
        String firstCarNumber = car1.getNumber();
        String secondCarNumber = car2.getNumber();
        firstParking.addAuto(car1);
        firstParking.addAuto(car2);
        assertThat(firstParking.getFreeSpacesForCars()).isEqualTo(3);
        assertThat(firstParking.getParkingCarsList().get(0).getNumber()).isEqualTo(firstCarNumber);
        assertThat(firstParking.getParkingCarsList().get(1).getNumber()).isEqualTo(secondCarNumber);
        assertThat(firstParking.getFreeSpacesForTrucks()).isEqualTo(5);
        assertThat(firstParking.removeAuto(car1)).isTrue();
        assertThat(firstParking.getFreeSpacesForCars()).isEqualTo(4);
        assertThat(firstParking.removeAuto(secondCarNumber)).isTrue();
        assertThat(firstParking.removeAuto(secondCarNumber)).isFalse();
        assertThat(firstParking.getFreeSpacesForCars()).isEqualTo(5);
    }

}