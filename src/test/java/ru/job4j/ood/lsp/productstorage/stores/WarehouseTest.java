package ru.job4j.ood.lsp.productstorage.stores;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.model.Food;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WarehouseTest {

    @Test
    void  whenAddOneProduct() {
        Store warehouse = new Warehouse();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        boolean result = warehouse.addToStorage(bread);
        assertThat(result).isTrue();
    }

    @Test
    void  whenAddSameProduct() {
        Store warehouse = new Warehouse();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        boolean result = warehouse.addToStorage(bread);
        assertThat(result).isTrue();
        result = warehouse.addToStorage(bread);
        assertThat(result).isFalse();
        int countProductsInWarehouse = warehouse.getNumbersOfProducts();
        assertThat(countProductsInWarehouse).isEqualTo(1);
    }

    @Test
    void  whenToGetStorage() {
        Store warehouse = new Warehouse();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        warehouse.addToStorage(bread);
        warehouse.addToStorage(milk);
        List<Food> expected = List.of(bread, milk);
        List<Food> warehouseStorage = warehouse.getStorage();
        assertThat(expected).isEqualTo(warehouseStorage);
        int countProductsInWarehouse = warehouse.getNumbersOfProducts();
        assertThat(countProductsInWarehouse).isEqualTo(2);
    }

    @Test
    void  whenClearStorage() {
        Store warehouse = new Warehouse();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        warehouse.addToStorage(milk);
        List<Food> expected = List.of(milk);
        List<Food> warehouseStorage = warehouse.getStorage();
        assertThat(expected).isEqualTo(warehouseStorage);
        int countProductsInWarehouse = warehouse.getNumbersOfProducts();
        assertThat(countProductsInWarehouse).isEqualTo(1);
        warehouse.clearStorage();
        assertThat(List.of()).isEqualTo(warehouse.getStorage());
        assertThat(warehouse.getNumbersOfProducts()).isEqualTo(0);
    }

    @Test
    void  whenGetNamesOfProductsFromStorage() {
        Store warehouse = new Warehouse();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        warehouse.addToStorage(bread);
        warehouse.addToStorage(milk);
        String expected = "bread, milk";
        String result = warehouse.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenGetNamesOfProductsFromEmptyStorage() {
        Store warehouse = new Warehouse();
        String expected = "";
        String result = warehouse.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenGetNamesOfProductsFromStorageAfterClear() {
        Store warehouse = new Warehouse();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        warehouse.addToStorage(bread);
        warehouse.addToStorage(milk);
        String expected = "bread, milk";
        String result = warehouse.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
        warehouse.clearStorage();
        expected = "";
        result = warehouse.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenUsingCorrectCondition() {
        Store warehouse = new Warehouse();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        boolean result = warehouse.checkCondition(milk, 25.0);
        assertThat(result).isTrue();
    }

    @Test
    void  whenUsingWrongCondition() {
        Store warehouse = new Warehouse();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        boolean result = warehouse.checkCondition(milk, 25.1);
        assertThat(result).isFalse();
    }

    @Test
    void  whenGetCorrectClassName() {
        Store warehouse = new Warehouse();
        String result = warehouse.getClassName();
        assertThat(result).isEqualTo("Warehouse");
    }

    @Test
    void  whenGetWrongClassName() {
        Store warehouse = new Warehouse();
        String result = warehouse.getClassName();
        assertThat(result).isNotEqualTo("warehouse");
    }
}