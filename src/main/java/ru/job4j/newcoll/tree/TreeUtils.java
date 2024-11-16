package ru.job4j.newcoll.tree;


import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreeUtils<T> {

    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     */
    public int countNode(Node<T> root) {
        return findAllValues(root).size();
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     */
    public Iterable<T> findAll(Node<T> root) {
        return findAllValues(root);
    }

    /**
     * Служебный метод выполняет обход дерева и возвращает коллекцию(список) ключей узлов дерева
     * @param root корневой узел
     * @return коллекция(список) с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
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


    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     */
    public boolean add(Node<T> root, T parent, T child) {
        boolean success = false;
        Optional<Node<T>> result = findByKey(root, parent);
        if (result.isPresent()) {
            List<Node<T>> children = result.get().getChildren();
            Node<T> newChild = new Node<>(child);
            if (!children.contains(newChild)) {
                success = children.add(newChild);
            }
        }
        return success;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        return findOrDivideByKey(root, key, false);
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        return findOrDivideByKey(root, key, true);
    }

    /**
     * Служебный метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * если параметр needDelete == true, из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @param needDelete нужно ли удалять найденный элемент и всё его поддерево
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    private Optional<Node<T>> findOrDivideByKey(Node<T> root, T key, boolean needDelete) {
        /* Да, возможно, немного "наговнокодил" в этом методе, но зато код не дублируется в двух предыдущих методах )))) */
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        Optional<Node<T>> result = Optional.empty();
        int count = 0;
        boolean wasFound = false;
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (root.getValue().equals(key)) {
            result = Optional.of(root);
        } else {
            stack.push(root);
            count++;
            while (count != 0) {
                Node<T> tempNode = stack.pop();
                count--;
                List<Node<T>> children = tempNode.getChildren();
                if (children.size() > 0) {
                    for (Node<T> oneChild : children) {
                        if (oneChild.getValue().equals(key)) {
                            result = Optional.of(oneChild);
                            if (needDelete) {
                                children.remove(oneChild);
                            }
                            wasFound = true;
                            break;
                        }
                        stack.push(oneChild);
                        count++;
                    }
                    if (wasFound) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}