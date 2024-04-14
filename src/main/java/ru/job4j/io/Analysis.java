package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> linesBuffer = new ArrayList<>();
        String start = "", finish = "";
        boolean serverOnline = true;
        try (BufferedReader input = new BufferedReader(new FileReader(source))) {
            linesBuffer = input.lines().filter(line -> line.matches("\\d{3}\s\\d{2}:\\d{2}:\\d{2}")).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String oneLine : linesBuffer) {
            String[] stringsTempArr = oneLine.split(" ", 2);
            if (serverOnline) {
                if (Integer.parseInt(stringsTempArr[0]) > 399) {
                    start = stringsTempArr[1];
                    serverOnline = false;
                }
            } else if (Integer.parseInt(stringsTempArr[0]) < 400) {
                finish = stringsTempArr[1];
                serverOnline = true;
                stringBuilder.append(String.format("%s;%s;%n", start, finish));
            }
        }
        try (BufferedWriter output = new BufferedWriter(new FileWriter(target))) {
            output.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}