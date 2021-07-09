package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(cachingDir + key))) {
            String string;
            while ((string = reader.readLine()) != null) {
                rsl.append(string).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl.toString();
    }
}