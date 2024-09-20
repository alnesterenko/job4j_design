package ru.job4j.ood.lsp.parking.tools;

import java.util.Random;

public class RandomInt {

    private Random random;

    public RandomInt() {
        this.random = new Random();
    }

    public int fromZeroTo(int max) {
        return random.nextInt(max);
    }

    public int intBetween(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
