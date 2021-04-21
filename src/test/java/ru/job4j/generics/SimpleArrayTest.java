package ru.job4j.generics;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleArrayTest {

    @Test
    public void whenAddElements() {
        SimpleArray<Object> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add("Dog");
        assertEquals(simpleArray.get(0), 1);
        assertEquals(simpleArray.get(1), 2);
        assertEquals(simpleArray.get(2), "Dog");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetIndexOutOfBounds() {
        SimpleArray<Object> simpleArray = new SimpleArray<>(3);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add("Dog");
        simpleArray.get(3);
    }

    @Test
    public void whenSetElements() {
        SimpleArray<Object> simpleArray = new SimpleArray<>(3);
        simpleArray.add("1");
        simpleArray.add("Two");
        simpleArray.add(new Animal());
        simpleArray.set(2, 123);
        assertEquals(simpleArray.get(2), 123);
    }

    @Test
    public void whenRemoveMiddleElement() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.add(4);
        simpleArray.add(5);
        simpleArray.remove(2);
        assertEquals((Integer)simpleArray.get(2), (Integer)4);
    }
}