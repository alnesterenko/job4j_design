package ru.job4j.ood.lsp.productstorage.stores;

import ru.job4j.ood.lsp.productstorage.model.Food;

import java.util.function.Predicate;

public class Shop extends AbstractStore {
    private Predicate<Double> specialCondition = exhaustion -> exhaustion >= 75 && exhaustion < 100;

    public Shop() {
        super(exhaustion -> exhaustion > 25 && exhaustion < 100);
    }

    /**
     * Проще всего если Shop сам будет применять скидку.
     * Так он ПОЧТИ не будет выделяться из списка хранилищ.
     */
    @Override
    public boolean checkCondition(Food food, Double percentage) {
        if (checkSpecialCondition(percentage) && !food.isHasDiscount() && super.getStorage().contains(food)) {
            food.setPrice(food.getNewPriceWithDiscount());
            food.setHasDiscount(true);
        }
        return super.getCondition().test(percentage);
    }

    private boolean checkSpecialCondition(Double percentage) {
        return specialCondition.test(percentage);
    }
}
