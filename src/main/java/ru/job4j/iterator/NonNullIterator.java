package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] == null) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

}

/*
* Ответ от ИИ:
* Вы можете улучшить этот код, добавив проверку на null в конструкторе NonNullIterator,
*  чтобы исключить возможность передачи массива с null значениями.
*  Это поможет избежать неожиданного поведения и упростит логику методов hasNext() и next().

Source: ru.job4j.iterator.NonNullIterator
* */