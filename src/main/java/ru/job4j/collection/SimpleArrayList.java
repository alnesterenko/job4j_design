package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    /*private int cursor;*/

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            doublingSize(container);
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T replacedElement = get(index);
        container[index] = newValue;
        return replacedElement;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void doublingSize(T[] array) {
        if (container.length == 0) {
            container = Arrays.copyOf(array, 1);
        } else {
            container = Arrays.copyOf(array, array.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            int expectedModCount = modCount;

            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                   throw  new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                   throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}