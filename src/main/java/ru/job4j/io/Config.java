package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line = read.readLine();
            while (line != null) {
                if (!line.startsWith("#") && !line.isEmpty()) {
                    String[] pair = line.trim().split("=", -1);
                    values.put(pair[0].trim(), pair[1].trim());
                }
                line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String rsl = values.get(key);
        if (rsl.equals("")) {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}