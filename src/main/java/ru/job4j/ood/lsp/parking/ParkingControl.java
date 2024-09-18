package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.auto.Auto;
import ru.job4j.ood.lsp.parking.parkings.Parking;

public class ParkingControl {
    private Parking parking;

    public ParkingControl(Parking parking) {
        this.parking = parking;
    }

    public boolean addAutoToParking(Auto auto) {
        return false;
    }

    public boolean removeAutoFromParking(Auto auto) {
        return parking.removeAuto(auto);
    }

    public boolean removeAutoFromParking(String number) {
        return parking.removeAuto(number);
    }

    /**
     * Самый главный метод в этом классе. Проверяет возможно ли поставить авто на стоянку.
     * @param auto -- авто которое нужно добавить на стоянку
     * @return true или false
     */
    public boolean checkPossibleAddAutoToParking(Auto auto) {
        return false;
    }

    public Parking getParking() {
        return parking;
    }
}
