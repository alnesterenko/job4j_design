package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.auto.Car;
import ru.job4j.ood.lsp.parking.auto.Truck;
import ru.job4j.ood.lsp.parking.parkings.Parking;
import ru.job4j.ood.lsp.parking.parkings.ParkingOnTwoArrayLists;

import static org.assertj.core.api.Assertions.*;

class ParkingControlTest {

    @Test
    void  whenAddOneTruck() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        ParkingControl parkingControl = new ParkingControl(firstParking);
        Auto truck = new Truck();
        parkingControl.addAutoToParking(truck);
        assertThat(parkingControl.getParking().getFreeSpacesOnTrucksParking()).isEqualTo(4);
    }

    @Test
    void  whenAddTwoCarsAndRemoveBoth() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        ParkingControl parkingControl = new ParkingControl(firstParking);
        Auto car1 = new Car();
        Auto car2 = new Car();
        String secondCarNumber = car2.getNumber();
        parkingControl.addAutoToParking(car1);
        assertThat(parkingControl.addAutoToParking(car2)).isTrue();
        assertThat(parkingControl.getParking().getFreeSpacesOnCarsParking()).isEqualTo(3);
        assertThat(parkingControl.getParking().getFreeSpacesOnTrucksParking()).isEqualTo(5);
        assertThat(parkingControl.removeAutoFromParking(car1)).isTrue();
        assertThat(parkingControl.removeAutoFromParking(car1)).isFalse();
        assertThat(parkingControl.removeAutoFromParking(secondCarNumber)).isTrue();
        assertThat(parkingControl.getParking().getFreeSpacesOnCarsParking()).isEqualTo(5);
    }
}