package ru.job4j.algo.hash;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {

    /**
     * Метод ищет в строке самую длинную подстроку, состоящую из уникальных символов.
     *
     * В методе формируется(разными способами) три "скользящих окна".
     * Если заморочиться, то можно ещё несколько способов придумать.
     * Но, думаю, в данном случае и этих трёх будет достаточно.
     * @param str строка в которой нужно найти самую длинную подстроку, состоящую из уникальных символов.
     * @return самая длинную подстрока, состоящую из уникальных символов.
     *          Если найденны несколько равных по длине, то возвращается первая найденная.
     */
    public static String longestUniqueSubstring(String str) {
        String result = "";
        if (!str.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                String window = str.substring(i);
                String subString = checkUniqueCharacters(window);
                if (result.length() < subString.length()) {
                    result = subString;
                }
                window = str.substring(0, (str.length() - 1) - i);
                subString = checkUniqueCharacters(window);
                if (result.length() < subString.length()) {
                    result = subString;
                }
                if (i < (str.length() - 1) - i) {
                    window = str.substring(i, (str.length() - 1) - i);
                    subString = checkUniqueCharacters(window);
                    if (result.length() < subString.length()) {
                        result = subString;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Метод принимает строку и превращает её в набор УНИКАЛЬНЫХ символов.
     * Если размер набора равен длине строки, значит все символы в этой строке были уникальны(не повторялись).
     * @param str строка, которую нужно проверить: не повторяются ли в ней символы.
     * @return если символы в строке не повторяются, то возвращается эта строка,
     *          а если символы повторяются, то возвращается пустая стока("").
     */
    private static String checkUniqueCharacters(String str) {
        String result = "";
        Set<Character> tempHashSet = str.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
        if (tempHashSet.size() == str.length()) {
           result = str;
        }
        return result;
    }
}