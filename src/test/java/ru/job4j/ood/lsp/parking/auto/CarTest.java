package ru.job4j.ood.lsp.parking.auto;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    void  whenGetSizeOfCar() {
        Auto car = new Car();
        assertThat(car.getSize()).isEqualTo(1);
    }

    @Test
    void  whenNeedToGetClassName() {
        Auto car = new Car();
        String result = car.getClassName();
        assertThat(result).isNotEqualTo("chupocaber");
        assertThat(result).isEqualTo("Car");
    }

    @Test
    void  whenNeedToGetNumber() {
        Auto car = new Car();
        String result = car.getNumber();
        assertThat(result).isNotEqualTo("chupocaber");
        assertThat(result).isNotEqualTo("Car");
        assertThat(result.getClass().getSimpleName()).isEqualTo("String");
    }

    @Test
    @Disabled
    void  whenCheckUniqueNumbers() {
        Auto car = new Car();
        String firstNumber = car.getUniqueNumber();
        String secondNumber = car.getUniqueNumber();
        assertThat(firstNumber).isNotEqualTo(secondNumber);
    }

}