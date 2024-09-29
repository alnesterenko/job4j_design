package ru.job4j.ood.lsp.productstorage;

import ru.job4j.ood.lsp.productstorage.tools.DateView;
import ru.job4j.ood.lsp.productstorage.model.Food;
import ru.job4j.ood.lsp.productstorage.stores.Store;
import ru.job4j.ood.lsp.productstorage.tools.DoubleRounding;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ControlQuality {
    private final List<Store> storeList;

    public ControlQuality(List<Store> storeList) {
        this.storeList = storeList;
    }

    /**
     * Метод добавляет один продукт в раннее определённое хранилище.
     * @param food -- добавляемый продукт
     * @param dateAdded -- дата добавления
     * @return -- true или false(добавлен или нет).
     * (По-моему, возвращаемый результат определяется не совсем правильно, но я считаю,
     *  вероятность погрешности минимальна.)
     */
    public boolean addProducts(Food food, LocalDate dateAdded) {
        Optional<Store> storeForAdd = checkQualityOfOneProduct(food, dateAdded);
        return storeForAdd.isPresent() && storeForAdd.get().addToStorage(food);
    }

    public boolean addProducts(List<Food> foodList, LocalDate dateAdded) {
        int count = 0;
        for (Food oneFood : foodList) {
            if (addProducts(oneFood, dateAdded)) {
                count++;
            }
        }
        return count > 0;
    }

    /**
     * Метод узнаёт израсходованность качества продукта и определяет в какое хранилище(Store) его нужно поместить.
     * !!! Метод продукт НИГДЕ НЕ РАЗМЕЩАЕТ, а только определяет, где его нужно разместить.
     * !!! ДОБАВЛЕНА ПРОВЕРКА НА ТОТ СЛУЧАЙ, ЕСЛИ СПИСОК ХРАНИЛИЩ(storeList) ПУСТОЙ !!!
     * @param food -- продукт который нужно куда-то определить.
     * @param dateOfInspection -- дата проверки качества продукта(условно говоря: текущая дата)
     * @return Optional<Store> -- опционал с объектом Store в который нужно поместить продукт.
     */
    public Optional<Store> checkQualityOfOneProduct(Food food, LocalDate dateOfInspection) {
        Optional<Store> result = Optional.ofNullable(null);
        if (storeList.size() > 0) {
        double exhaustionPercentageOfQuality = calculateExhaustionPercentage(food, dateOfInspection);
            for (Store oneStore : storeList) {
                if (oneStore.checkCondition(food, exhaustionPercentageOfQuality)) {
                    result = Optional.ofNullable(oneStore);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод перепроверяет правильность нахождения продуктов в соответствующих хранилищах(Store),
     * в зависимости от даты проверки.
     * @param dateOfInspection -- дата проверки.
     * @return Map<String, String> -- Мапа-отчёт, где первая String -- Название хранилища,
     * а вторая String -- список названий продуктов в этом хранилище.
     */
    public Map<String, String> resort(LocalDate dateOfInspection) {
        /* Здесь заменять new LinkedHashMap<>() на абстрактную Map не буду,
        * потому что тут важно сохранение последовательности добавления. */
        Map<String, String> result = new LinkedHashMap<>();
        if (storeList.size() > 0) {
            List<Food> tempList = new ArrayList<>();
            for (Store oneStore : storeList) {
                tempList.addAll(oneStore.getStorage());
                oneStore.clearStorage();
                Iterator<Food> tempListIterator = tempList.iterator();
                while (tempListIterator.hasNext()) {
                    Food nextFood = tempListIterator.next();
                    if (this.addProducts(nextFood, dateOfInspection)) {
                        tempListIterator.remove();
                    }
                }
                result.put(oneStore.getClassName(), oneStore.getNamesOfProducts());
            }
        }
        return result;
    }

    /**
     * Метод высчитывает ПРОЦЕНТАЖ ИСПОРЧЕННОСТИ(НЕСВЕЖЕСТИ) ПРОДУКТА.
     * @param food -- "обследуемый" продукт
     * @param dateOfInspection -- дата "обследования"(текущая дата)
     * @return процент испорченности продукта(с точностью двух знаков после точки(запятой))
     * @throws IllegalArgumentException -- если дата проверки происходит до даты выпуска продукта,
     *  выбрасывается исключение.
     */
    public double calculateExhaustionPercentage(Food food, LocalDate dateOfInspection) throws IllegalArgumentException {
        LocalDate createDate = food.getCreateDate();
        LocalDate expiryDate = food.getExpiryDate();
        int expirationDays = (int) ChronoUnit.DAYS.between(createDate, expiryDate);
        if (dateOfInspection.isBefore(createDate)) {
            throw new IllegalArgumentException(
                    String.format("Дата проверки(%s) происходит до даты выпуска(%s) продукта(%s)",
                            dateOfInspection.format(DateView.useMyPattern()),
                            createDate.format(DateView.useMyPattern()),
                            food.getName()));
        }
        int daysBetweenCreateAndInspection = (int) ChronoUnit.DAYS.between(createDate, dateOfInspection);
        return DoubleRounding.roundingToTwoDigit((double) 100 / expirationDays * daysBetweenCreateAndInspection);
    }
}
