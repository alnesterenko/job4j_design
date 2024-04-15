package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) throws IOException {
        String[] startFinish = {"", ""};
        boolean[] serverOnline = {true};
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             BufferedWriter output = new BufferedWriter(new FileWriter(target))) {
            input.lines().filter(line -> line.matches("\\d{3}\s\\d{2}:\\d{2}:\\d{2}")).forEach((line) -> {
                String[] stringsTempArr = line.split(" ", 2);
                if (serverOnline[0]) {
                    if (Integer.parseInt(stringsTempArr[0]) > 399) {
                        startFinish[0] = stringsTempArr[1];
                        serverOnline[0] = false;
                    }
                } else if (Integer.parseInt(stringsTempArr[0]) < 400) {
                    startFinish[1] = stringsTempArr[1];
                    serverOnline[0] = true;
                    try {
                        output.write(String.format("%s;%s;%n", startFinish[0], startFinish[1]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}