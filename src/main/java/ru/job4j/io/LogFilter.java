package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> resultList = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            resultList = input.lines().filter(line -> {
                String[] tempStringArr = line.split(" ");
                return tempStringArr.length >= 2 && "404".equals(tempStringArr[tempStringArr.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            for (String oneLine : data) {
                output.printf("%s%n", oneLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}