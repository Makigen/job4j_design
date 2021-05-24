package ru.job4j.serialization.json;

public class Engine {
    private final String vincode;

    public Engine(String vincode) {
        this.vincode = vincode;
    }

    public String getVincode() {
        return vincode;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "vincode='" + vincode + '\''
                + '}';
    }
}