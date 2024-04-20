package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (parametersValidation(args)) {
            search(Paths.get(args[0]), path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static boolean parametersValidation(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Incorrect parameters. "
                    + "The first parameter must point to the folder being scanned. "
                    + "The second is for the extension of the files we are looking for."
                    + "For example: C:\\, .js");
        }
        File file = new File(args[0]);
        String extension = args[1];
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        if (!extension.matches("\\.[a-z0-9]{2,5}$")) {
            throw new IllegalArgumentException(String.format("Incorrect extension: %s", extension));
        }
        return true;
    }
}