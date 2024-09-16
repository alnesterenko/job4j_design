package ru.job4j.ood.lsp.productstorage.stores;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store {
    private List<Food> storage = new ArrayList<>();
    private Predicate<Double> condition;

    public AbstractStore(Predicate<Double> condition) {
        this.condition = condition;
    }

    /**
     * Добавляет ОДИН продукт в хранилище
     * Перед добавлением проверяет: нет ли там уже этого продукта
     * @param food -- добавляемый продукт
     * @return получилось(true) или нет(false)
     */
    @Override
    public boolean addToStorage(Food food) {
        return !storage.contains(food) && storage.add(food);
    }

    /**
     * Возвращает всё хранилище продуктов
     * @return List<Food> -- ссылка на хранилище продуктов
     */
    @Override
    public List<Food> getStorage() {
        return this.storage;
    }

    /**
     * Метод для полной очистки хранилища.
     * Спорный метод, но ОН ОЧЕНЬ НУЖЕН в классе ControlQuality.
     */
    @Override
    public void clearStorage() {
        this.storage = new ArrayList<>();
    }

    /**
     * Возвращает количество продуктов в хранилище
     * @return количество продуктов в хранилище
     */
    @Override
    public int getNumbersOfProducts() {
        return storage.size();
    }

    /**
     * Ввозвращает имена продуктов находящихся в хранилище
     * (!!! Метод нужен для тестов !!!)
     * @return Строка с именами продуктов(через запятую)
     */
    @Override
    public String getNamesOfProducts() {
        StringJoiner result = new StringJoiner(", ");
        for (Food oneFood : storage) {
            result.add(oneFood.getName());
        }
        return result.toString();
    }

    /**
     * !!! Самый важный метод в этом классе !!!
     * Проверяет соответствует ли продукт условиям нахождения в этом хранилище
     * @param food -- нужен для того, чтобы в классе Shop можно было применить скидку к продукту
     *            и установить соответствующий флаг.
     * @param percentage -- проценты израсходованности годности продукта
     * @return true или false
     */
    @Override
    public boolean checkCondition(Food food, Double percentage) {
        return condition.test(percentage);
    }

    /**
     * Возвращает ОСНОВНОЕ predicate-условие для нахождения в этом хранилище
     * @return predicate-условие
     */
    protected Predicate getCondition() {
        return condition;
    }

    /**
     * Этот метод нужен для создания удобного отчёта в классе ControlQuality.
     * А также будет использоваться в тестах.
     * @return String -- имя текущего хранилища.(Без полного пути)
     */
    @Override
    public String getClassName() {
        String fullName = this.getClass().getName();
        return fullName.substring(fullName.lastIndexOf(".") + 1);
    }
}
