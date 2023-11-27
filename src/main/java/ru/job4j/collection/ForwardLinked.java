package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> link = head;
        Node<T> newNode = new Node<T>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            while (link.next != null) {
                link = link.next;
            }
            link.next = newNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> next = head;
        for (int i = 0; i < index; i++) {
            next = next.next;
        }
        return next.item;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T tempValue;
        Node<T> tempNode;
        tempValue = head.item;
        tempNode = head.next;
        head.item = null;
        head.next = null;
        head = tempNode;
        size--;
        modCount++;
        return tempValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int expectedModCount = modCount;

            Node<T> link;
            Node<T> next = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                link = next;
                next = next.next;
                return link.item;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}