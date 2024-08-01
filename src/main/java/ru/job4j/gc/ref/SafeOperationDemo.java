package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SafeOperationDemo {

    public static void main(String[] args) throws InterruptedException {
        /* Создаём список МЯГКИХ ссылок на имена и заполняем его */
        List<SoftReference<String>> names = new ArrayList<>();
        names.add(new SoftReference<>("Пантелеймон"));
        names.add(new SoftReference<>("Филипок"));
        names.add(new SoftReference<>("Евлампий"));

        /* Создаём список СЛАБЫХ ссылок на номера и заполняем его */
        List<WeakReference<Integer>> numbers = new ArrayList<>();
        numbers.add(new WeakReference<>(200));
        numbers.add(new WeakReference<>(300));
        numbers.add(new WeakReference<>(400));

        /* Блок используемый для "теста" */
        /*System.gc();
        TimeUnit.SECONDS.sleep(3);*/

        /* Сначала проверяем, не удалил ли GC список имён.(а вдруг!).
        * А уже затем: если GC не удалил список номеров, то задействуем их в формировании строк.
        *  А если уже удалил, то используем 0. */
        if (names.get(0).get() != null) {
            int count = names.size();
            for (int i = 0; i < count; i++) {
                String strongName = names.get(i).get();
                int strongNumber = numbers.get(0).get() != null ? numbers.get(i).get() : 0;
                System.out.printf("%s_%d%n", strongName, strongNumber);
            }
        }
    }
}
