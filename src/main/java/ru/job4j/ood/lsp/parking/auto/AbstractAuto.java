package ru.job4j.ood.lsp.parking.auto;

import ru.job4j.ood.lsp.parking.tools.RandomInt;

public abstract class AbstractAuto implements Auto {
    private int size = 1;
    private final String number;

    public AbstractAuto() {
        this.number = getUniqueNumber();
    }

    /**
     * При создании каждого Auto ему будет присваиваться уникальный номер(индентификатор).
     * @return String -- уникальный индентификатор для авто.
     */
    @Override
    public String getUniqueNumber() {
        StringBuilder stringBuilder = new StringBuilder();
        RandomInt randomInt = new RandomInt();
        for (int i = 0; i < 3; i++) {
            stringBuilder.append(randomInt.intBetween(0, 9));
        }
        for (int i = 0; i < 3; i++) {
            stringBuilder.append((char) randomInt.intBetween(97, 122));
        }
        return stringBuilder.toString();
    }

    /**
     * Этот метод нужен для того чтобы отличать один тип автомобиля от другого(легковушку от грузовика)
     * и не добавлять для этого дополнительное поле в этот класс.
     * @return String -- Тип текущего автомобиля.(Без полного пути)
     */
    @Override
    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Возвращает размер авто
     * @return int -- размер авто
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Задаёт размер авто. Нужен чтобы создавать грузовики разного размера.
     * @param size -- новый размер авто
     */
    protected void setSize(int size) {
        this.size = size;
    }

    /**
     * Возвращает уникальный номер автомобиля
     * @return String -- уникальный номер(индентификатор)
     */
    @Override
    public String getNumber() {
        return this.number;
    }
}
