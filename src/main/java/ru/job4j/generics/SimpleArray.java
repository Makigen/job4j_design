package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] elements = {};
    private int index = 0;

    public SimpleArray(int capacity) {
        if (capacity >= 0) {
            this.elements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    public void add(T model) {
        elements[index++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, this.index);
        elements[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, this.index);
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        this.index--;
    }

    public T get(int index) {
        Objects.checkIndex(index, this.index);
        return (T) elements[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[point++];
            }
        };
    }
}
