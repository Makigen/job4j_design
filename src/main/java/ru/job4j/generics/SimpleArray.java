package ru.job4j.generics;

import java.util.Iterator;
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
        Objects.checkIndex(index, elements.length);
        elements[index++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, elements.length);
        elements[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, elements.length);
        Object[] dest = new Object[elements.length - 1];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        System.arraycopy(elements, 0, dest, 0, elements.length - 1);
        elements = dest;
    }

    public T get(int index) {
        return (T) elements[index];
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> itr = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
        return itr;
    }

    public static void main(String[] args) {
        SimpleArray<Object> numbers = new SimpleArray<>(10);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);
        numbers.set(0, 11);
        numbers.remove(1);
        int[] testArray = {1, 2, 3, 4, 5, 6};
        for (int i = 0; i < 9; i++) {
            System.out.println(numbers.get(i));
        }
    }
}
