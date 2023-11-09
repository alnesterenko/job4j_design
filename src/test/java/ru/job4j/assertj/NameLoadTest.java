package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void dontHaveSimbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(new String[]{"key:value"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key:value");
    }

    @Test
    void simbolAtTheBeginning() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(new String[]{"=value"}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=value");
    }

    @Test
    void simbolAtTheEnding() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(new String[]{"key="}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key=");
    }

    @Test
    void whenArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }
}