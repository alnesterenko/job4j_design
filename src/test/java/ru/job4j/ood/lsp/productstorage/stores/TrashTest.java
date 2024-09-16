package ru.job4j.ood.lsp.productstorage.stores;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.model.Food;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    void  whenAddOneProduct() {
        Store trash = new Trash();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        boolean result = trash.addToStorage(bread);
        assertThat(result).isTrue();
    }

    @Test
    void  whenAddSameProduct() {
        Store trash = new Trash();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        boolean result = trash.addToStorage(bread);
        assertThat(result).isTrue();
        result = trash.addToStorage(bread);
        assertThat(result).isFalse();
        int countProductsInTrash = trash.getNumbersOfProducts();
        assertThat(countProductsInTrash).isEqualTo(1);
    }

    @Test
    void  whenToGetStorage() {
        Store trash = new Trash();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        trash.addToStorage(bread);
        trash.addToStorage(milk);
        List<Food> expected = List.of(bread, milk);
        List<Food> trashStorage = trash.getStorage();
        assertThat(expected).isEqualTo(trashStorage);
        int countProductsInTrash = trash.getNumbersOfProducts();
        assertThat(countProductsInTrash).isEqualTo(2);
    }

    @Test
    void  whenClearStorage() {
        Store trash = new Trash();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        trash.addToStorage(milk);
        List<Food> expected = List.of(milk);
        List<Food> trashStorage = trash.getStorage();
        assertThat(expected).isEqualTo(trashStorage);
        int countProductsInTrash = trash.getNumbersOfProducts();
        assertThat(countProductsInTrash).isEqualTo(1);
        trash.clearStorage();
        assertThat(List.of()).isEqualTo(trash.getStorage());
        assertThat(trash.getNumbersOfProducts()).isEqualTo(0);
    }

    @Test
    void  whenGetNamesOfProductsFromStorage() {
        Store trash = new Trash();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        trash.addToStorage(bread);
        trash.addToStorage(milk);
        String expected = "bread, milk";
        String result = trash.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenGetNamesOfProductsFromEmptyStorage() {
        Store trash = new Trash();
        String expected = "";
        String result = trash.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenGetNamesOfProductsFromStorageAfterClear() {
        Store trash = new Trash();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        trash.addToStorage(bread);
        trash.addToStorage(milk);
        String expected = "bread, milk";
        String result = trash.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
        trash.clearStorage();
        expected = "";
        result = trash.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenUsingCorrectCondition() {
        Store trash = new Trash();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        boolean result = trash.checkCondition(milk, 100.0);
        assertThat(result).isTrue();
    }

    @Test
    void  whenUsingWrongCondition() {
        Store trash = new Trash();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        boolean result = trash.checkCondition(milk, 99.9);
        assertThat(result).isFalse();
    }

    @Test
    void  whenGetCorrectClassName() {
        Store trash = new Trash();
        String result = trash.getClassName();
        assertThat(result).isEqualTo("Trash");
    }

    @Test
    void  whenGetWrongClassName() {
        Store trash = new Trash();
        String result = trash.getClassName();
        assertThat(result).isNotEqualTo("trash");
    }
}