package ru.job4j.collection;

public interface SimpleLinked<E> extends Iterable<E> {
    void add(E value);
    E get(int index);
}
/* Это специальный комментарий, чтобы можно было повторно закоммитить этот файл */