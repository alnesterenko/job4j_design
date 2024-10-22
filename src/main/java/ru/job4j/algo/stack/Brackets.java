package ru.job4j.algo.stack;

import java.util.*;

public class Brackets {

    public boolean isValid(String s) {
        boolean result = true;
        if (!"".equals(s)) {
           if (s.length() % 2 == 1) {
               result = false;
           } else {
               Map<String, String> bracketsVariants = new HashMap<>();
               bracketsVariants.put("(", ")");
               bracketsVariants.put("{", "}");
               bracketsVariants.put("[", "]");
               Deque<String> stack = new ArrayDeque<>();
               for (String oneBracket : s.split("")) {
                   String tempBracket = bracketsVariants.get(oneBracket);
                   if (tempBracket != null) {
                       stack.push(tempBracket);
                   } else if (stack.isEmpty()  || !stack.pop().equals(oneBracket)) {
                       result = false;
                       break;
                   }
               }
           }
        }
        return result;
    }
}
