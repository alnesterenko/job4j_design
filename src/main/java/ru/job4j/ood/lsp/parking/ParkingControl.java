package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.parkings.Parking;

public class ParkingControl {
    private Parking parking;

    public ParkingControl(Parking parking) {
        this.parking = parking;
    }

    public boolean addAutoToParking(Auto auto) {
        return parking.addAuto(auto);
    }

    public boolean removeAutoFromParking(Auto auto) {
        return parking.removeAuto(auto);
    }

    public boolean removeAutoFromParking(String number) {
        return parking.removeAuto(number);
    }

    public Parking getParking() {
        return parking;
    }
}
