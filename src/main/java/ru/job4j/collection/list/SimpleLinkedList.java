package ru.job4j.collection.list;

import ru.job4j.collection.ForwardLinked;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<E> node(int index) {
        Objects.checkIndex(index, size);
        Node<E> n = first;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }

    @Override
    public void add(E value) {
        Node<E> n = last;
        Node<E> newNode = new Node<>(n, value, null);
        last = newNode;
        if (n == null) {
            first = newNode;
        } else {
            n.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return node(index).item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = first;
            final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
            return point < size;
        }

            @Override
            public E next() {

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                E item = node.item;
                node = node.next;
                point++;
                return item;
            }
        };
    }
}