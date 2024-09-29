package ru.job4j.ood.lsp.productstorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.stores.Shop;
import ru.job4j.ood.lsp.productstorage.stores.Store;
import ru.job4j.ood.lsp.productstorage.stores.Trash;
import ru.job4j.ood.lsp.productstorage.stores.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    public static ControlQuality controlQuality;

    @BeforeEach
    public void init() {
        List<Store> storeList = List.of(
                new Warehouse(),
                new Shop(),
                new Trash());
        ControlQualityTest.controlQuality = new ControlQuality(storeList);
    }

    @Test
    void  whenDateOfInspectionIsBeforeCreateDate() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 15),
                22);
        LocalDate dateOfInspection = LocalDate.of(2024, 9, 9);
        assertThatThrownBy(() -> controlQuality.calculateExhaustionPercentage(bread, dateOfInspection))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void  whenExhaustionPercentageMastBe40percent() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 20),
                22);
        LocalDate dateOfInspection = LocalDate.of(2024, 9, 14);
        double result = controlQuality.calculateExhaustionPercentage(bread, dateOfInspection);
        assertThat(result).isEqualTo(40, withPrecision(0.01d));
    }

    @Test
    void  whenExhaustionPercentageMastBe36point3percent() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        LocalDate dateOfInspection = LocalDate.of(2024, 9, 14);
        double result = controlQuality.calculateExhaustionPercentage(bread, dateOfInspection);
        assertThat(result).isEqualTo(36.3, withPrecision(0.1d));
    }

    @Test
    void  whenAddOneProduct() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        boolean result = controlQuality.addProducts(bread, dateAdded);
        assertThat(result).isTrue();
    }

    @Test
    void  whenAddSameProduct() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        boolean result = controlQuality.addProducts(bread, dateAdded);
        assertThat(result).isTrue();
        result = controlQuality.addProducts(bread, dateAdded);
        assertThat(result).isFalse();
    }

    @Test
    void  whenAddSameProductsInList() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        controlQuality.addProducts(bread, dateAdded);
        List<Food> productsList = List.of(bread, bread);
        boolean result = controlQuality.addProducts(productsList, dateAdded);
        assertThat(result).isFalse();
    }

    @Test
    void  whenAddDifferentProductsInList() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                40);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        controlQuality.addProducts(bread, dateAdded);
        List<Food> productsList = List.of(bread, milk);
        boolean result = controlQuality.addProducts(productsList, dateAdded);
        assertThat(result).isTrue();
    }

    @Test
    void  tryToCheckQualityOfOneProductWhenStoreListIsEmpty() {
        ControlQualityTest.controlQuality = new ControlQuality(new ArrayList<>());
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        Optional<Store> result = controlQuality.checkQualityOfOneProduct(bread, dateAdded);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    void  tryAddProductWhenStoreListIsEmpty() {
        ControlQualityTest.controlQuality = new ControlQuality(new ArrayList<>());
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        boolean result = controlQuality.addProducts(bread, dateAdded);
        assertThat(result).isFalse();
    }

    @Test
    void  whenAddProductInWarehouse() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 20),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 11);
        Optional<Store> result = controlQuality.checkQualityOfOneProduct(bread, dateAdded);
        assertThat(result.orElse(new Trash()).getClassName()).isEqualTo("Warehouse");
    }

    @Test
    void  whenAddProductInShop() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 20),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 13);
        Optional<Store> result = controlQuality.checkQualityOfOneProduct(bread, dateAdded);
        assertThat(result.orElse(new Trash()).getClassName()).isEqualTo("Shop");
    }

    @Test
    void  whenAddProductInShopWithDiscount() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 20),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 18);
        controlQuality.addProducts(bread, dateAdded);
        Optional<Store> result = controlQuality.checkQualityOfOneProduct(bread, dateAdded);
        Store store = result.orElse(new Trash());
        assertThat(store.getClassName()).isEqualTo("Shop");
        assertThat(store.getStorage().get(0).isHasDiscount()).isTrue();
        assertThat(store.getStorage().get(0).getPrice()).isEqualTo(17.6, withPrecision(0.1d));
    }

    @Test
    void  whenAddProductInTrashOn100Percent() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 20),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 20);
        Optional<Store> result = controlQuality.checkQualityOfOneProduct(bread, dateAdded);
        assertThat(result.orElse(new Warehouse()).getClassName()).isEqualTo("Trash");
    }

    @Test
    void  whenAddProductInTrashAfter100Percent() {
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 20),
                22);
        LocalDate dateAdded = LocalDate.of(2024, 9, 21);
        Optional<Store> result = controlQuality.checkQualityOfOneProduct(bread, dateAdded);
        assertThat(result.orElse(new Warehouse()).getClassName()).isEqualTo("Trash");
    }

    @Test
    void  whenAddProductsThenControlQuality() {
        Food bread = new Food("хлеб",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 14),
                22);
        Food milk = new Food("молоко",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 18),
                40);
        Food pate = new Food("паштет",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 30),
                35);
        LocalDate dateAdded = LocalDate.of(2024, 9, 10);
        controlQuality.addProducts(List.of(bread, milk, pate), dateAdded);
        LocalDate dateOfInspection = LocalDate.of(2024, 9, 10);
        Map<String, String> tempResult = controlQuality.resort(dateOfInspection);
        assertThat(tempResult.get("Warehouse")).isEqualTo("хлеб, молоко, паштет");
        /* Следующая проверка. Спустя 2 дня. */
        dateOfInspection = LocalDate.of(2024, 9, 12);
        tempResult = controlQuality.resort(dateOfInspection);
        assertThat(tempResult.get("Warehouse")).isEqualTo("молоко, паштет");
        assertThat(tempResult.get("Shop")).isEqualTo("хлеб");
        /* Следующая проверка. Спустя 2 дня. */
        dateOfInspection = LocalDate.of(2024, 9, 14);
        tempResult = controlQuality.resort(dateOfInspection);
        assertThat(tempResult.get("Warehouse")).isEqualTo("паштет");
        assertThat(tempResult.get("Shop")).isEqualTo("молоко");
        assertThat(tempResult.get("Trash")).isEqualTo("хлеб");
    }
}