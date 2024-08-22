package ru.job4j.ood.srp;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

/**
 * Здесь практически тоже самое: в одном классе два не взаимосвязанных функционала.
 *
 *  Чтобы это исправить нужно разделить класс на 2 части:
 *  - разделить интерфейс на 2 части, что бы один класс, реализующий интерфейс,
 *    только генерировал,
 *    а другой класс, реализующий второй интерфейс, только печатал.
 *
 */
public class SimpleSequenceGenerator implements SequenceGenerator<Integer> {
    @Override
    public List<Integer> generate(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .map(i -> random.nextInt()).boxed()
                .collect(Collectors.toList());
    }

    @Override
    public void print(List<Integer> numbers) {
        numbers.forEach(System.out::println);
    }
}