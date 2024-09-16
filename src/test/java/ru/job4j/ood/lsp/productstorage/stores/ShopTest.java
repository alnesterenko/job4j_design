package ru.job4j.ood.lsp.productstorage.stores;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.productstorage.model.Food;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class ShopTest {

    @Test
    void  whenAddOneProduct() {
        Store shop = new Shop();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        boolean result = shop.addToStorage(bread);
        assertThat(result).isTrue();
    }

    @Test
    void  whenAddSameProduct() {
        Store shop = new Shop();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        boolean result = shop.addToStorage(bread);
        assertThat(result).isTrue();
        result = shop.addToStorage(bread);
        assertThat(result).isFalse();
        int countProductsInShop = shop.getNumbersOfProducts();
        assertThat(countProductsInShop).isEqualTo(1);
    }

    @Test
    void  whenToGetStorage() {
        Store shop = new Shop();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        shop.addToStorage(bread);
        shop.addToStorage(milk);
        List<Food> expected = List.of(bread, milk);
        List<Food> shopStorage = shop.getStorage();
        assertThat(expected).isEqualTo(shopStorage);
        int countProductsInShop = shop.getNumbersOfProducts();
        assertThat(countProductsInShop).isEqualTo(2);
    }

    @Test
    void  whenClearStorage() {
        Store shop = new Shop();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        shop.addToStorage(milk);
        List<Food> expected = List.of(milk);
        List<Food> shopStorage = shop.getStorage();
        assertThat(expected).isEqualTo(shopStorage);
        int countProductsInShop = shop.getNumbersOfProducts();
        assertThat(countProductsInShop).isEqualTo(1);
        shop.clearStorage();
        assertThat(List.of()).isEqualTo(shop.getStorage());
        assertThat(shop.getNumbersOfProducts()).isEqualTo(0);
    }

    @Test
    void  whenGetNamesOfProductsFromStorage() {
        Store shop = new Shop();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        shop.addToStorage(bread);
        shop.addToStorage(milk);
        String expected = "bread, milk";
        String result = shop.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenGetNamesOfProductsFromEmptyStorage() {
        Store shop = new Shop();
        String expected = "";
        String result = shop.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenGetNamesOfProductsFromStorageAfterClear() {
        Store shop = new Shop();
        Food bread = new Food("bread",
                LocalDate.of(2024, 9, 10),
                LocalDate.of(2024, 9, 21),
                22);
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        shop.addToStorage(bread);
        shop.addToStorage(milk);
        String expected = "bread, milk";
        String result = shop.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
        shop.clearStorage();
        expected = "";
        result = shop.getNamesOfProducts();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    void  whenUsingCorrectCondition() {
        Store shop = new Shop();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        boolean result = shop.checkCondition(milk, 25.1);
        assertThat(result).isTrue();
        result = shop.checkCondition(milk, 99.9);
        assertThat(result).isTrue();
    }

    @Test
    void  whenUsingWrongCondition() {
        Store shop = new Shop();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        boolean result = shop.checkCondition(milk, 25.0);
        assertThat(result).isFalse();
        result = shop.checkCondition(milk, 100.0);
        assertThat(result).isFalse();
    }

    @Test
    void  whenGetCorrectClassName() {
        Store shop = new Shop();
        String result = shop.getClassName();
        assertThat(result).isEqualTo("Shop");
    }

    @Test
    void  whenGetWrongClassName() {
        Store shop = new Shop();
        String result = shop.getClassName();
        assertThat(result).isNotEqualTo("shop");
    }

    /* Далее идут методы предназначенные только для класса Shop */

    @Test
    void  whenUsingSpecialCondition() {
        Store shop = new Shop();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        shop.addToStorage(milk);
        boolean result = shop.checkCondition(milk, 75.0);
        assertThat(result).isTrue();
        boolean hasDiscount = shop.getStorage().get(0).isHasDiscount();
        assertThat(hasDiscount).isTrue();
        double priceAfterDiscount = shop.getStorage().get(0).getPrice();
        assertThat(priceAfterDiscount).isEqualTo(32, withPrecision(0.01d));
    }

    @Test
    void  whenNotUsingSpecialCondition() {
        Store shop = new Shop();
        Food milk = new Food("milk",
                LocalDate.of(2024, 9, 11),
                LocalDate.of(2024, 9, 30),
                40);
        shop.addToStorage(milk);
        boolean result = shop.checkCondition(milk, 74.9);
        assertThat(result).isTrue();
        boolean hasDiscount = shop.getStorage().get(0).isHasDiscount();
        assertThat(hasDiscount).isFalse();
        double price = shop.getStorage().get(0).getPrice();
        assertThat(price).isNotEqualTo(32);
        assertThat(price).isEqualTo(40);
    }
}