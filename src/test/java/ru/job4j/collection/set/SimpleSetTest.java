package ru.job4j.collection.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddObject() {
        Set<Object> set = new SimpleSet<>();
        Object o = new Object();
        assertTrue(set.add(o));
        assertTrue(set.contains(o));
        assertFalse(set.add(o));
    }

    @Test
    public void whenAddStrings() {
        Set<String> set = new SimpleSet<>();
        String first = "String";
        String second = "String";
        String third = "string";
        assertTrue(set.add(first));
        assertFalse(set.contains(third));
        assertFalse(set.add(second));
    }
}