package ru.job4j.ood.dip;

import java.util.ArrayList;

/**
 * Класс SomeClass в методе firstMethod() принимает ArrayList чисел с плавающей точкой
 * и возвращает ArrayList целых чисел. Здесь нарушается принцып DIP.
 * Из-за своей "жёсткой привязки" к ArrayList, этот метод уже LinkedList обработать не сможет.
 * Чтобы небыло нарушения принцыпа DIP необходимо:
 * 1) Заменить ArrayList в типе возвращаемого значения и в принимаемых параметрах на List.
 *      И тогда firstMethod() сможет обрабатывать и возвращать любые типы List-ов.
 */

public class SomeClass {
    public ArrayList<Integer> firstMethod(ArrayList<Boolean> integerArrayList) {
        /* Какая-то своя внутренняя логика, например: округление каждого значения до целого числа в большую сторону. */
        return new ArrayList<Integer>();
    }
}
