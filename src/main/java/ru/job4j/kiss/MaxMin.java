package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T rsl = value.get(0);
        for (T t : value) {
            if (comparator.compare(rsl, t) < 0) {
                rsl = t;
            }
        }
        return rsl;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T rsl = value.get(0);
        for (T t : value) {
            if (comparator.compare(rsl, t) > 0) {
                rsl = t;
            }
        }
        return rsl;
    }
}