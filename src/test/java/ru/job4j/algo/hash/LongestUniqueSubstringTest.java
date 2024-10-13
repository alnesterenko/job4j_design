package ru.job4j.algo.hash;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LongestUniqueSubstringTest {
    @Test
    public void whenStringIsEmpty() {
        String str = "";
        assertThat(LongestUniqueSubstring.longestUniqueSubstring(str)).isEqualTo("");
    }

    @Test
    public void whenStringHasUniqueCharacters() {
        String str = "abcde";
        assertThat(LongestUniqueSubstring.longestUniqueSubstring(str)).isEqualTo("abcde");
    }

    @Test
    public void whenStringHasRepeatedCharacters() {
        String str = "abcbcde";
        assertThat(LongestUniqueSubstring.longestUniqueSubstring(str)).isEqualTo("bcde");
    }

    @Test
    public void whenStringHasAllRepeatedCharacters() {
        String str = "aaaaa";
        assertThat(LongestUniqueSubstring.longestUniqueSubstring(str)).isEqualTo("a");
    }

    @Test
    public void whenCorrectCombinationAtTheBeginningOfTheWord() {
        String str = "bcdeabbc";
        assertThat(LongestUniqueSubstring.longestUniqueSubstring(str)).isEqualTo("cdeab");
    }

    @Test
    public void whenCorrectCombinationInsideTheWord() {
        String str = "aabccc";
        assertThat(LongestUniqueSubstring.longestUniqueSubstring(str)).isEqualTo("abc");
    }
}
