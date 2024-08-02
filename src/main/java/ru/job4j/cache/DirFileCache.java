package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        try (Stream<String> lines = Files.lines(Paths.get(cachingDir + "/" + key))) {
            lines.forEach(stringJoiner::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String finalString = stringJoiner.toString();
        return finalString;
    }
}