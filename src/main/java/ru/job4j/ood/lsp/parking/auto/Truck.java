package ru.job4j.ood.lsp.parking.auto;

import ru.job4j.ood.lsp.parking.tools.RandomInt;

public class Truck extends AbstractAuto {
    private static final int MINSIZE = 2;
    private final int maxSize = 4;

    public Truck() {
        super();
        super.setSize(getRandomSize(MINSIZE, maxSize));
    }

    /**
     * Будет выдавать случайный размер грузовика в диапазоне от 2(включительно) до 4(включительно).
     * @return int -- размер грузовика
     */
    private int getRandomSize(int minSize, int maxSize) {
        RandomInt randomInt = new RandomInt();
        return randomInt.intBetween(minSize, maxSize);
    }

    public int getMaxSize() {
        return maxSize;
    }
}
