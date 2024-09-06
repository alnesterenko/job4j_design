package ru.job4j.ood.lsp;

/**
 * !!! Подкласс не должен требовать от вызывающего кода больше, чем базовый класс !!!
 * Чтобы создать экземпляр класса-наследника класса А(класс В),
 * нужно знать(!!!), что это можно сделать только запустив статический метод initialize() класса В.
 */

public class A {
    private Integer firstField;
    private String secondField;

    public A(Integer firstField, String secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }

    public Integer getFirstField() {
        return firstField;
    }

    public String getSecondField() {
        return secondField;
    }

    public void setFirstField(Integer firstField) {
        this.firstField = firstField;
    }

    public void setSecondField(String secondField) {
        this.secondField = secondField;
    }
}

class B extends A {

    private B(Integer firstField, String secondField) {
        super(firstField, secondField);
    }

    public static B initialize(Integer firstParameter, String secondParameter) {
        return new B(firstParameter, secondParameter);
    }
}

class ClassToCheck {
    public static void main(String[] args) {
        A a = new A(1, "first");
        System.out.println(a.getFirstField());
        System.out.println(a.getSecondField());
        /* B b = new B(2, "second"); */ /* Работать не будет */
        B b = B.initialize(3, "third"); /* Нужно знать правильный способ создания наследника класса А */
        System.out.println(b.getFirstField());
        System.out.println(b.getSecondField());
    }
}
