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
        /*if (size == 0) {
           head = new Node<E>(value, null);
        } else {
            Node<E> previousNode = head;
            for (int i = 0; i < size - 1; i++) {
                previousNode = previousNode.next;
            }
            previousNode.next = new Node<E>(value, null);
        }
        size++;
        modCount++;*/
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int expectedModCount = modCount;

            Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                return size != 0 && currentNode.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> temp = currentNode.next;
                currentNode = temp;
                return temp.item;
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