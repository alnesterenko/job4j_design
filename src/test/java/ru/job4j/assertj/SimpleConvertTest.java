package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(3).isNotNull().isEqualTo("four");
        assertThat(list).last().isNotNull();
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).allSatisfy(elem -> {
                    assertThat(elem.length()).isLessThan(10);
                    assertThat(elem.length()).isGreaterThan(3);
                });
        assertThat(set).isNotEmpty().hasSize(5).contains("first", "five", "four")
                .containsAnyOf("two hundred", "one hundred", "three");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .containsKeys("first", "three", "second")
                .containsValues(3, 1, 2)
                .doesNotContainKey("zero")
                .doesNotContainValue(7)
                .containsEntry("second", 1);
    }
}