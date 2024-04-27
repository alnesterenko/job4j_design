package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            System.out.println(randomAccess.getFilePointer()); // узнать текущее положение курсора
            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(4);
            randomAccess.seek(randomAccess.length()); // установка указателя(курсора) в конец записи
            System.out.println("Положение указателя после boolean: " + randomAccess.getFilePointer());
            randomAccess.writeDouble(3.01);
            randomAccess.seek(7);
            System.out.println(randomAccess.readDouble());
            System.out.println(randomAccess.readChar());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}