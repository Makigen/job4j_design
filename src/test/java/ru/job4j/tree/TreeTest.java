package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddFalse() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.add(2, 2),
                is(false)
        );
    }

    @Test
    public void whenAddToChild() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(2, 3);
        assertThat(
                tree.findBy(3).isPresent(),
                is(true)
        );
    }

    @Test
    public void whenTreeIsBinary() {
        Tree<Integer> tree = new SimpleTree<>(4);
        tree.add(4, 3);
        tree.add(4, 5);
        tree.add(3, 1);
        tree.add(3, 2);
        tree.add(2, 1);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }

    @Test
    public void whenTreeIsNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(4);
        tree.add(4, 3);
        tree.add(4, 5);
        tree.add(4, 6);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }
}