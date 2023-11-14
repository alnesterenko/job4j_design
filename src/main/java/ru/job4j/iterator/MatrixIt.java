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
        int rowLimit = data.length - 1;
        if (column >= data[row].length && row < rowLimit) {
            row++;
            column = 0;
        }
        while (data[row].length == 0 && row < rowLimit) {
            row++;
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