package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, HashSet<Path>> resultMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        File tempFile = file.toFile();
        resultMap.computeIfAbsent(new FileProperty(tempFile.length(), tempFile.getName()),
                value -> new HashSet<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, HashSet<Path>> entry : resultMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                FileProperty tempFileProperty = entry.getKey();
                System.out.printf("%s - %.2f Кб%n", tempFileProperty.getName(), (double) tempFileProperty.getSize() / 1024);
                for (Path path : entry.getValue()) {
                    System.out.println("\t" + path.toAbsolutePath());
                }
            }
        }
    }
}
