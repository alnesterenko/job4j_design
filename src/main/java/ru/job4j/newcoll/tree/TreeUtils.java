package ru.job4j.newcoll.tree;


import ru.job4j.collection.SimpleQueue;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        return findAllValues(root).size();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        return findAllValues(root);
    }

    private List<T> findAllValues(Node<T> root) {
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        List<T> listOfNodes = new ArrayList<>();
        if (root == null) {
            throw new IllegalArgumentException();
        }
        queue.push(root);
        while (!queue.isEmpty()) {
            Node<T> tempNode = queue.poll();
            listOfNodes.add(tempNode.getValue());
            for (Node<T> oneChild : tempNode.getChildren()) {
                queue.push(oneChild);
            }
        }
        return listOfNodes;
    }
}