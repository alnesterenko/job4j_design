package ru.job4j.map;

import java.util.*;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int basket = getNumberOfBasket(key);
        if (table[basket] == null) {
            table[basket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        int h = Objects.hashCode(hashCode);
        return (hashCode == 0) ? 0 : (h ^ (h >>> 16));
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry oneElement : table) {
            if (oneElement != null) {
                newTable[getNumberOfBasket((K) oneElement.key)] = oneElement;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int basket = getNumberOfBasket(key);
        if (checkBasketByNumber(key, basket)) {
                result = table[basket].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int basket = getNumberOfBasket(key);
        if (checkBasketByNumber(key, basket)) {
            table[basket] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }

    private int getNumberOfBasket(K key) {
        int hashCode = Objects.hashCode(key);
        if (hashCode != 0) {
            hashCode = indexFor(hash(hashCode));
        }
        return hashCode;
    }

    private boolean checkBasketByNumber(K key, int basket) {
        boolean result = false;
        if (table[basket] != null) {
            K keyFromTable = table[basket].key;
            if (Objects.hashCode(key) == Objects.hashCode(keyFromTable)
                    && Objects.equals(key, keyFromTable)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>()  {
            private int index;
            int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw  new ConcurrentModificationException();
                }
                while (index < table.length && table[index] == null) {
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println(map.hash(65536));
        System.out.println(map.indexFor(8));
    }
}