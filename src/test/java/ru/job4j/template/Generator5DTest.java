package ru.job4j.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class Generator5DTest {
    Map<String, String> map = new HashMap<>();
    String template = "I am a ${name}, Who are ${subject}? ";

    @BeforeEach
    public void setUp() {
        map.clear();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
    }

    @Test
    public void whenTheTemplateContainsKeysThatAreNotInTheMapThenGetException() {
        Generator generator = new Generator5D();
        map.remove("name");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenThereAreExtraKeysInTheMapThenGetException() {
        Generator generator = new Generator5D();
        map.put("job", "road worker");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenAllGood() {
        Generator generator = new Generator5D();
        assertThat(generator.produce(template, map)).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }
}