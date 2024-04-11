package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                int tempInt = Integer.parseInt(line);
                if (tempInt % 2 == 0) {
                    System.out.println(tempInt + " // чётное");
                } else {
                    System.out.println(tempInt + " // не чётное");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
