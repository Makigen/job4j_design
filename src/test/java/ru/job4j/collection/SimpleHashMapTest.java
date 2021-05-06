package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    @Test
    public void whenAddThenIter() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "One");
        map.insert(2, "Two");
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenNeedToGrow() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        for (int i = 0; i < 50; i++) {
            map.insert((i*i + "ZY"), "String");
        }
        map.insert("1911", "Grow");
        assertThat(map.get("1911"), is("Grow"));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "One");
        map.insert(2, "Two");
        map.delete(1);
        Iterator<Integer> it = map.iterator();
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "first");
        Iterator<Integer> it = map.iterator();
        map.insert(2, "second");
        it.next();
    }
}
