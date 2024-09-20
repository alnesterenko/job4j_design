package ru.job4j.ood.lsp.parking.auto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class TruckTest {

    @Test
    void  whenNeedToGetClassName() {
        Auto truck = new Truck();
        String result = truck.getClassName();
        assertThat(result).isNotEqualTo("chupocaber");
        assertThat(result).isEqualTo("Truck");
    }

    @Test
    void  whenNeedToGetNumber() {
        Auto truck = new Truck();
        String result = truck.getNumber();
        assertThat(result).isNotEqualTo("chupocaber");
        assertThat(result).isNotEqualTo("Car");
        assertThat(result).isNotEqualTo("Truck");
        assertThat(result.getClass().getSimpleName()).isEqualTo("String");
    }

    @Test
    void  whenCheckUniqueNumbers() {
        Auto truck = new Truck();
        String firstNumber = truck.getUniqueNumber();
        String secondNumber = truck.getUniqueNumber();
        assertThat(firstNumber).isNotEqualTo(secondNumber);
    }

    @Test
    void  whenCheckHaveTruckUniqueNumbersAndSizeBetween2And4() {
        Auto truck = new Truck();
        assertThat(truck.getNumber()).isNotEqualTo("");
        assertThat(truck.getSize()).isGreaterThan(1).isLessThan(5);
    }
}