package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream output = new FileOutputStream("data/dataresult.txt")) {
            output.write(System.lineSeparator().getBytes());
            for (int i = 1; i <= 10; i++) {
                String tempString = new String("1 * " + i + " = " + i);
                output.write(tempString.getBytes());
                output.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
