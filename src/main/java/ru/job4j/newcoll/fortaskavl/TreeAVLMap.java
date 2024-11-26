package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(value) && Objects.nonNull(key)) {
            if (!contains(key)) {
                root = put(root, key, value);
            } else {
                Node tempNode = find(root, key);
                tempNode.value = value;
            }
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = put(node.left, key, value);
            } else {
                node.right = put(node.right, key, value);
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    private boolean contains(T key) {
        return find(root, key) != null;
    }

    private Node find(Node node, T key) {
        Node result = null;
        if (node != null) {
            /*if (node.getText().equals(key.toString())) {*/
            if (Objects.equals(node.key, key)) {
                result = node;
            } else {
                int comparingResult = node.key.compareTo(key);
                if (comparingResult < 0) {
                    result = find(node.right, key);
                } else {
                    result = find(node.left, key);
                }
            }
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T element) {
        if (node == null) {
            return null;
        }
        int comparisonResult = element.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, element);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, element);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    /*private T minimum() {
        return Objects.nonNull(root) ? minimum(root).key : null;
    }*/

    private Node minimum(Node node) {
        Node result;
        if (node.left == null) {
            result = node;
        } else {
            result = minimum(node.left);
        }
        return result;
    }

    /*private T maximum() {
        return Objects.nonNull(root) ? maximum(root).key : null;
    }*/

    private Node maximum(Node node) {
        Node result;
        if (node.right == null) {
            result = node;
        } else {
            result = maximum(node.right);
        }
        return result;
    }

    public V get(T key) {
        Node tempNode = find(root, key);
        return  Objects.nonNull(tempNode) ? tempNode.value : null;
    }

    public Set<T> keySet() {
        Set<T> resultSet = new LinkedHashSet<>();
        List<Node> tempList = inSymmetricalOrder();
        for (Node oneNode : tempList) {
           resultSet.add(oneNode.key);
        }
        return resultSet;
    }

    public Collection<V> values() {
        List<V> resultList = new ArrayList<>();
        List<Node> tempList = inSymmetricalOrder();
        for (Node oneNode : tempList) {
            resultList.add(oneNode.value);
        }
        return resultList;
    }

    private List<Node> inSymmetricalOrder() {
        List<Node> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private List<Node> inSymmetricalOrder(Node localRoot, List<Node> list) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, list);
            list.add(localRoot);
            inSymmetricalOrder(localRoot.right, list);
        }
        return list;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
