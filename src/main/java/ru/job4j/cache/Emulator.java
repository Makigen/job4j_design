package ru.job4j.cache;

public class Emulator {
    public static void main(String[] args) {
        DirFileCache dirFileCache = new DirFileCache("src/main/resources/");
        System.out.println(dirFileCache.get("app.properties"));
        System.out.println(dirFileCache.get("log4j.properties"));
    }
}
