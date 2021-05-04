package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private final int initialCapacity = 16;
    private Object[] storage = new Object[initialCapacity];
    private int size = 0;
    private int modCount = 0;

    public SimpleHashMap() {
    }

    public SimpleHashMap(int size) {
        this.size = size;
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private int hash(K key) {
        return key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int getIndex(int hash) {
        return hash % (storage.length - 1);
    }

    private void grow() {
        storage = Arrays.copyOf(storage, storage.length * 2);
        modCount++;
    }

    public boolean insert(K key, V value) {
        Node<K, V> node = new Node<>(hash(key), key, value);
        int index = getIndex(hash(key));
        if (size >= storage.length) {
            grow();
        }
        if (storage[index] == null) {
        storage[index] = node;
        size++;
        modCount++;
        return true;
        }
        return false;
    }

    public V get(K key) {
        V value = null;
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = (Node<K, V>) storage[index];
        if (key == node.getKey()) {
            value = node.getValue();
        }
        return value;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = (Node<K, V>) storage[index];
        if (node == null || !node.getKey().equals(key)) {
            return false;
        }
        storage[index] = null;
        modCount++;
        size--;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                while (point < storage.length && storage[point] == null) {
                    point++;
                }
                return point <= size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return ((Node<K, V>) storage[point++]).getKey();
            }
        };
    }
}