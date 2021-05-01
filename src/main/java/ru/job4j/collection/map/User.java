package ru.job4j.collection.map;

import java.util.Calendar;

public class User {

    private String name;
    private int chlidren;
    private Calendar birthday;

    public User(String name, int chlidren, Calendar birthday) {
        this.name = name;
        this.chlidren = chlidren;
        this.birthday = birthday;
    }
}
