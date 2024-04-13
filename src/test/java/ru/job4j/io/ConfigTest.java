package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenAllGood() {
        Config config = new Config("data/app.properties");
        config.load();
        assertThat(config.value("config")).isEqualTo("java");
    }

    @Test
    void whenWithoutValue() {
        Config config = new Config("data/wrongProperties/withoutValue.properties");
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeyAndValueTogether() {
        Config config = new Config("data/wrongProperties/keyValueTogether.properties");
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenWithoutKey() {
        Config config = new Config("data/wrongProperties/withoutKey.properties");
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenJustOneEquals() {
        Config config = new Config("data/wrongProperties/justOneEquals.properties");
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenHaveEmptyLines() {
        Config config = new Config("data/wrongProperties/withEmptyLines.properties");
        config.load();
        assertThat(config.value("config")).isEqualTo("java");
    }

    @Test
    void whenHaveComments() {
        Config config = new Config("data/wrongProperties/withComments.properties");
        config.load();
        assertThat(config.value("config")).isEqualTo("java");
    }

    @Test
    void whenKeyEqualsValueEquals() {
        Config config = new Config("data/wrongProperties/keyEqualsValueEquals.properties");
        config.load();
        assertThat(config.value("key")).isEqualTo("value=");
    }

    @Test
    void whenKeyEqualsValueEquals1() {
        Config config = new Config("data/wrongProperties/keyEqualsValueEquals1.properties");
        config.load();
        assertThat(config.value("key")).isEqualTo("value=1");
    }
}