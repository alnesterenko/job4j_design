package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T needToBeReturned;
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
        needToBeReturned = out.pop();
        size--;
        for (int i = 0; i < size; i++) {
            in.push(out.pop());
        }
        return needToBeReturned;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}