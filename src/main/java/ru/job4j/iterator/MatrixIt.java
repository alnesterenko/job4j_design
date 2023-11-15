package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length) {
            if (column >= data[row].length) {
                if (row < data.length - 1) {
                    column = 0;
                    row++;
                } else {
                    break;
                }
            }
            if (data[row].length != 0 && data[row][column] != 0) {
                break;
            } else {
                row++;
            }
        }
        return row < data.length && column < data[row].length && data[row][column] != 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}