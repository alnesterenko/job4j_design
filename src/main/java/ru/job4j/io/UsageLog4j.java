package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String string = "Some string";
        int anyDigit = 33;
        byte smallDigit = 127;
        short mediumDigit = 32111;
        double pi = 3.14D;
        float digitWithPoint = 1.27F;
        long bigDigit = 42_327_467_246_781L;
        char oneCharacter = 'c';
        boolean flag = true;
        LOG.debug("{}Checking information output. String: {}, int: {}, byte: {}, short: {}, double: {}, "
                       + "float: {}, long: {}, char: {}, boolean: {}.",
                System.lineSeparator(), string, anyDigit, smallDigit, mediumDigit, pi, digitWithPoint, bigDigit, oneCharacter, flag);

    }
}