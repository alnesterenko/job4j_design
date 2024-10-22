package ru.job4j.algo.stack;

import java.util.*;

public class Brackets {

    public boolean isValid(String s) {
        boolean result = "".equals(s);
        if (!result && s.length() % 2 == 0) {
            result = true;
            Map<String, String> bracketsVariants = new HashMap<>();
            bracketsVariants.put("(", ")");
            bracketsVariants.put("{", "}");
            bracketsVariants.put("[", "]");
            var splitArr = s.split("");
            int lengthOfBracketArr = splitArr.length;
            for (int i = 0; i < lengthOfBracketArr; i++) {
                if (!bracketsVariants.containsKey(splitArr[i])) {
                    result = false;
                    break;
                } else if (bracketsVariants.get(splitArr[i]).equals(splitArr[i + 1])) {
                    i++;
                } else if (bracketsVariants.get(splitArr[i]).equals(splitArr[splitArr.length - 1 - i])) {
                    lengthOfBracketArr--;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
