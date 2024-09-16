package ru.job4j.ood.lsp.productstorage.stores;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.util.List;

public interface Store {
    boolean addToStorage(Food food);
    List<Food> getStorage();
    void clearStorage();
    int getNumbersOfProducts();
    String getNamesOfProducts();
    /* !!! В следующем методе параметр food нужен для реализации ОСОБОЙ проверки в классе Shop */
    boolean checkCondition(Food food, Double percentage);
    String getClassName();
}
