package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {

    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        String separator = System.lineSeparator();
        File source = tempDir.resolve("server.log").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01" + separator
                    + "500 10:57:01" + separator
                    + "400 10:58:01" + separator
                    + "30010:58:31$gddg...server error!" + separator
                    + "300 10:59:01" + separator
                    + "500 11:01:02" + separator
                    + "200 11:02:02" + separator
                    + "400 11:03:02" + separator
                    + "" + separator
                    + "200 11:04:02" + separator);
        }
        File target = tempDir.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(line -> result.append(line + separator));
        }
        assertThat("10:57:01;10:59:01;" + separator
                + "11:01:02;11:02:02;" + separator
                + "11:03:02;11:04:02;" + separator).hasToString(result.toString());
    }
}