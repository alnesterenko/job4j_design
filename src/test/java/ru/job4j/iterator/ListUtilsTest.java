package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;
    private List<Integer> needToRemove = List.of(3, 5, 7, 9);

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterEnd() {
        ListUtils.addAfter(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 3, 2);
        ListUtils.addAfter(input, 2, 57);
        assertThat(input).hasSize(4).containsSequence(1, 3, 2, 57);
    }

    @Test
    void removeIfMoreThan2() {
        ListUtils.removeIf(input, elem -> elem > 2);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void removeIfGreaterThanOrEqualTo3() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 57);
        ListUtils.addAfter(input, 3, 1);
        assertThat(input).hasSize(5).containsSequence(1, 3, 2, 57, 1);
        ListUtils.removeIf(input, elem -> elem >= 3);
        assertThat(input).hasSize(3).containsSequence(1, 2, 1);
    }

    @Test
    void replaceIfGreaterThanOrEqualTo3To0() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 57);
        ListUtils.addAfter(input, 3, 1);
        assertThat(input).hasSize(5).containsSequence(1, 3, 2, 57, 1);
        ListUtils.replaceIf(input, elem -> elem >= 3, 0);
        assertThat(input).hasSize(5).containsSequence(1, 0, 2, 0, 1);
    }

    @Test
    void replaceIfGreaterThanOrEqualTo3To41() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 57);
        ListUtils.addAfter(input, 3, 1);
        assertThat(input).hasSize(5).containsSequence(1, 3, 2, 57, 1);
        ListUtils.replaceIf(input, elem -> elem >= 3, 41);
        assertThat(input).hasSize(5).containsSequence(1, 41, 2, 41, 1);
    }

    @Test
    void removeAllInList() {
        ListUtils.addAfter(input, 1, 2);
        ListUtils.addAfter(input, 2, 57);
        ListUtils.addAfter(input, 3, 1);
        assertThat(input).hasSize(5).containsSequence(1, 3, 2, 57, 1);
        ListUtils.removeAll(input, needToRemove);
        assertThat(input).hasSize(4).containsSequence(1, 2, 57, 1);
    }
}