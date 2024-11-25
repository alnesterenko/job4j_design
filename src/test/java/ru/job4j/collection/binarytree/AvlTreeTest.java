package ru.job4j.collection.binarytree;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void simmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void preOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inPreOrder();
        assertThat(list).containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void postOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inPostOrder();
        assertThat(list).containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void checkContainsBeforeAndAfterInsert() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
        assertThat(tree.contains(tree.getRoot(), 8)).isFalse();
        assertThat(tree.insert(8)).isTrue();
        list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
        assertThat(tree.contains(tree.getRoot(), 8)).isTrue();
    }

    @Test
    void whenRemoveIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
        assertThat(tree.remove(5)).isTrue();
        list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 6, 7);
    }

    @Test
    void whenRemoveIsNotOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
        assertThat(tree.remove(9)).isFalse();
        list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenFindMinimumManyTimes() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.minimum()).isNull();
        tree.insert(2);
        assertThat(tree.minimum()).isEqualTo(2);
        tree.insert(1);
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenFindMaximumManyTimes() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.maximum()).isNull();
        tree.insert(2);
        assertThat(tree.maximum()).isEqualTo(2);
        tree.insert(1);
        assertThat(tree.maximum()).isEqualTo(2);
        tree.insert(3);
        assertThat(tree.maximum()).isEqualTo(3);
    }

    @Test
    void checkBalanceAfterInsert() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i += 2) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 3, 5, 7);
        assertThat(tree.insert(2)).isTrue();
        list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 5, 7);
        assertThat(tree.insert(4)).isTrue();
        list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 7);
    }

    @Test
    void whenInsertTwoTimesOneValue() {
        AvlTree<Integer> tree = new AvlTree<>();
        assertThat(tree.insert(3)).isTrue();
        assertThat(tree.insert(3)).isFalse();
        assertThat(tree.insert(4)).isTrue();
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(3, 4);
    }
}