package ru.job4j.ood.dip;

/**
 * Здесь класс Book зависит от конкретной реализации класса ConsolePrinter,
 * экземпляр которого содержится в одном из полей класса Book.
 * Чтобы не нарушать принцып DIP нужно:
 * 1) Создать интерфейс IPrinter.
 * 2) Класс ConsolePrinter должен имплиментировать интерфейс IPrinter.
 * 3) В классе Book тип поля printer должен быть не ConsolePrinter, а IPrinter.
 * 4) В классе Book должен быть конструктор, принимающий ввиде параметра реализацию интерфейса IPrinter.
 */

public class Book {
    public String text;
    public ConsolePrinter printer;

    public void print() {
        printer.print(text);
    }
    /* Геттеры и сеттеры я не стал прописывать чтобы не увиличивать количество строк в файле. */
}

class ConsolePrinter {
    public void print(String text) {
        System.out.println(text);
    }
}
