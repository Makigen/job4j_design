package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        mem.set(mem.indexOf(findById(id)), model);
        return true;
    }

    @Override
    public boolean delete(String id) {
        mem.remove(findById(id));
        return true;
    }

    @Override
    public T findById(String id) {
        for (T model : mem) {
            if (model.getId().equals(id)) {
                return model;
            }
        }
        throw new NoSuchElementException();
    }
}