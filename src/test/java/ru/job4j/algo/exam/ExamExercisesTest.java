package ru.job4j.algo.exam;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ExamExercisesTest {

    @Test
    void whenManyRepetions() {
        int[] array = {1, 2, 3, 4, 2, 3, 4};
        int expectIndex = 1;
        int result = ExamExercises.findIndexFirstRepetition(array);
        assertThat(result).isEqualTo(expectIndex);
    }

    @Test
    void whenNoRepetions() {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int expectIndex = -1;
        int result = ExamExercises.findIndexFirstRepetition(array);
        assertThat(result).isEqualTo(expectIndex);
    }

    @Test
    void whenEmptyArray() {
        int[] array = {};
        int expectIndex = -1;
        int result = ExamExercises.findIndexFirstRepetition(array);
        assertThat(result).isEqualTo(expectIndex);
    }

    @Test
    void whenEnoughKeys() {
        int[][] building = {{1, 2}, {3}, {}};
        boolean result = ExamExercises.checkAllRooms(building);
        assertThat(result).isTrue();
    }

    @Test
    void whenEnoughKeys2() {
        int[][] building = {{1, 2}, {3}, {}, {}};
        boolean result = ExamExercises.checkAllRooms(building);
        assertThat(result).isTrue();
    }

    @Test
    void whenNotEnoughKeys1() {
        int[][] building = {{}, {3}, {}};
        boolean result = ExamExercises.checkAllRooms(building);
        assertThat(result).isFalse();
    }

    @Test
    void whenNotEnoughKeys2() {
        int[][] building = {{1, 2}, {3}, {}, {}, {}};
        boolean result = ExamExercises.checkAllRooms(building);
        assertThat(result).isFalse();
    }

    @Test
    void whenNotEnoughKeys3() {
        int[][] building = {{1, 2}, {}, {}, {3}, {}};
        boolean result = ExamExercises.checkAllRooms(building);
        assertThat(result).isFalse();
    }

    @Test
    void whenEmptyBuilding() {
        int[][] building = {};
        boolean result = ExamExercises.checkAllRooms(building);
        assertThat(result).isFalse();
    }
}