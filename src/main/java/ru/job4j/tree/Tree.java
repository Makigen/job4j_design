package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Tree<E> {

    boolean add(E parent, E child);

    boolean isBinary();

    Optional<Node<E>> findBy(E value);

    Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition);

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}