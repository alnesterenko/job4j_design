package ru.job4j.ood.lsp.productstorage.tools;

import java.time.format.DateTimeFormatter;

/**
 * Класс создан для того, чтобы выводить даты в удобном для меня формате.
 */
public class DateView {
    private static String pattern = "dd.MM.yyyy";

    public static DateTimeFormatter useMyPattern() {
        return DateTimeFormatter.ofPattern(pattern);
    }
}
