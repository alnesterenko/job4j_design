package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
            checkForKey(key);
            return values.get(key);
    }

    private void parse(String[] args) {
        for (String oneParameter : args) {
            String[] oneKeyPlusValue = oneParameter.split("=", 2);
            values.put(oneKeyPlusValue[0].trim().substring(1), oneKeyPlusValue[1].trim());
        }
    }

    public static ArgsName of(String[] args) {
        checkBeforePars(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void checkForKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (!key.matches("^[A-Za-z0-9\\.]+$")) {
            throw new IllegalArgumentException("The key can only contain letters, numbers and points");
        }
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
    }

    private static void checkBeforePars(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String oneParameter : args) {
            if (oneParameter.matches("^-=.+")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a key", oneParameter));
            }
            if (oneParameter.matches("^-[A-Za-z0-9\\.]+=$")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain a value", oneParameter));
            }
            if (!oneParameter.contains("=")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not contain an equal sign", oneParameter));
            }
            if (!oneParameter.startsWith("-")) {
                throw new IllegalArgumentException(
                        String.format("Error: This argument '%s' does not start with a '-' character", oneParameter));
            }
        }
    }

    public int getSize() {
        return values.size();
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}