package ru.job4j.collection.map;

import java.util.*;

public class User {

    private String name;
    private int chlidren;
    private Calendar birthday;

    public User(String name, int chlidren, Calendar birthday) {
        this.name = name;
        this.chlidren = chlidren;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return chlidren == user.chlidren && name.equals(user.name) && birthday.equals(user.birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Kirill", 2, new GregorianCalendar(1988, 6, 10));
        User user2 = new User("Kirill", 2, new GregorianCalendar(1988, 6, 10));

        Map<User, Object> map = new HashMap<>();

        map.put(user1, new Object());
        map.put(user2, new Object());

        System.out.println(map);
    }
}
