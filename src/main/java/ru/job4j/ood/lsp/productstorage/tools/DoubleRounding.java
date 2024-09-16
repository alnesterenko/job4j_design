package ru.job4j.ood.lsp.productstorage.tools;

public class DoubleRounding {
    public static double roundingToTwoDigit(double digit) {
        return (double) Math.round(digit * 100) / 100;
    }
}
