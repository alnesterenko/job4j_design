package ru.job4j.ood.lsp.productstorage.model;

import ru.job4j.ood.lsp.productstorage.tools.DateView;
import ru.job4j.ood.lsp.productstorage.tools.DoubleRounding;

import java.time.LocalDate;
import java.util.Objects;

public class Food {

    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private double price;
    private boolean hasDiscount = false;
    private final static double DISCOUNT = 20.0;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return DISCOUNT;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Food food)) {
            return false;
        }
        return Double.compare(getPrice(), food.getPrice()) == 0
                && Objects.equals(getName(), food.getName())
                && Objects.equals(getCreateDate(), food.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCreateDate(), getPrice());
    }


    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createDate=" + createDate.format(DateView.useMyPattern())
                + ", expiryDate=" + expiryDate.format(DateView.useMyPattern())
                + ", price=" + price
                + ", discount=" + DISCOUNT
                + '}';
    }


    /**
     * Я решил что:
     * 1) Класс Food будет сам вычислять свой размер скидки
     * 2) вычислять новую цену(уже со скидкой)
     */
    private double calculateDiscount() {
        return (price / 100) * DISCOUNT;
    }

    /**
     * Этот метод вычисляет новую цену со скидкой.
     * @return Возвращает новую цену округлённую(специально написанным классом) до двух знаков после запятой.
     * (!!! Возвращаемое значение возможно будет использоваться в тестах !!!)
     */
    public double getNewPriceWithDiscount() {
        return DoubleRounding.roundingToTwoDigit(getPrice() - calculateDiscount());
    }
}
