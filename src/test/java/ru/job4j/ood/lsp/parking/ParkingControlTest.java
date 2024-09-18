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

    @Disabled
    @Test
    void  whenAddOneTruck() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        ParkingControl parkingControl = new ParkingControl(firstParking);
        Auto truck = new Truck();
        parkingControl.addAutoToParking(truck);
        assertThat(parkingControl.getParking().getFreeSpacesForTrucks()).isEqualTo(4);
    }

    @Disabled
    @Test
    void  whenAddTwoCarsAndRemoveBoth() {
        Parking firstParking = new ParkingOnTwoArrayLists(5, 5);
        ParkingControl parkingControl = new ParkingControl(firstParking);
        Auto car1 = new Car();
        Auto car2 = new Car();
        String secondCarNumber = car2.getNumber();
        parkingControl.addAutoToParking(car1);
        assertThat(parkingControl.addAutoToParking(car2)).isTrue();
        assertThat(parkingControl.getParking().getFreeSpacesForCars()).isEqualTo(3);
        assertThat(parkingControl.removeAutoFromParking(car1)).isTrue();
        assertThat(parkingControl.removeAutoFromParking(car1)).isFalse();
        assertThat(parkingControl.removeAutoFromParking(secondCarNumber)).isTrue();
    }

    @Disabled
    @Test
    void  whenCheckCanAddCars() {
        Parking firstParking = new ParkingOnTwoArrayLists(2, 1);
        ParkingControl parkingControl = new ParkingControl(firstParking);
        Auto car1 = new Car();
        Auto car2 = new Car();
        Auto car3 = new Car();
        assertThat(parkingControl.checkPossibleAddAutoToParking(car1)).isTrue();
        parkingControl.addAutoToParking(car1);
        assertThat(parkingControl.checkPossibleAddAutoToParking(car2)).isTrue();
        assertThat(parkingControl.addAutoToParking(car2)).isTrue();
        assertThat(parkingControl.checkPossibleAddAutoToParking(car3)).isFalse();
        assertThat(parkingControl.addAutoToParking(car3)).isFalse();
    }

    @Disabled
    @Test
    void  whenCheckCanAddCarsAndTrucks() {
        Parking firstParking = new ParkingOnTwoArrayLists(2, 1);
        ParkingControl parkingControl = new ParkingControl(firstParking);
        Auto car1 = new Car();
        Auto car2 = new Car();
        Auto truck1 = new Truck();
        Auto truck2 = new Truck();
        parkingControl.addAutoToParking(car1);
        parkingControl.addAutoToParking(car2);
        assertThat(parkingControl.addAutoToParking(truck1)).isTrue();
        assertThat(parkingControl.checkPossibleAddAutoToParking(truck2)).isFalse();
        assertThat(parkingControl.addAutoToParking(truck2)).isFalse();
        parkingControl.removeAutoFromParking(car2);
        assertThat(parkingControl.checkPossibleAddAutoToParking(truck2)).isFalse();
        assertThat(parkingControl.addAutoToParking(truck2)).isFalse();
    }
}