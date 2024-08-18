package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void whenStartAtIs7() {
        Fool.setStartAt(7);
        String checkedString = Fool.check();
        assertThat(checkedString).isEqualTo("");
    }

    @Test
    void whenStartAtIs9() {
        Fool.setStartAt(9);
        String checkedString = Fool.check();
        assertThat(checkedString).isEqualTo("Fizz");
    }

    @Test
    void whenStartAtIs5() {
        Fool.setStartAt(5);
        String checkedString = Fool.check();
        assertThat(checkedString).isEqualTo("Buzz");
    }

    @Test
    void whenStartAtIs15() {
        Fool.setStartAt(15);
        String checkedString = Fool.check();
        assertThat(checkedString).isEqualTo("FizzBuzz");
    }

    @Test
    void whenCheckIsNotEquals() {
        Fool.setStartAt(15);
        String checkedString = Fool.check();
        assertThat(checkedString).isNotEqualTo("Fizz");
    }
}