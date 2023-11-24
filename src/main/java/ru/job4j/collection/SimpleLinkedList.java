package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> link = head;
        final Node<E> newNode = new Node<E>(value, null);
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Iterator<E> iterator = iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int expectedModCount = modCount;

            Node<E> link;
            Node<E> next = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                link = next;
                next = next.next;
                return link.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}