package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenArraySizeIsOddNumber() {
        int[] array = {10, 4, 6};
        assertThat(Merge.mergesort(array)).containsExactly(4, 6, 10);
    }

    @Test
    void whenHalfOfArrayFilledZero() {
        int[] array = {10, 0, 0, 0, 0, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 0, 0, 0, 0, 2, 3, 10);
    }

    @Test
    void whenOneNumberTheRestAreZeros() {
        int[] array = {1, 0, 0, 0, 0, 0, 0, 0};
        assertThat(Merge.mergesort(array)).containsExactly(0, 0, 0, 0, 0, 0, 0, 1);
    }
}