package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Car {
    private final boolean transmission;
    private final int year;
    private final Engine engine;
    private final String[] equipment;

    public Car(boolean transmission, int year, Engine engine, String[] equipment) {
        this.transmission = transmission;
        this.year = year;
        this.engine = engine;
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "Car{"
                + "transmission=" + transmission
                + ", year=" + year
                + ", engine=" + engine
                + ", equipment=" + Arrays.toString(equipment)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(true, 2021, new Engine("WVWZZZ3CZLE028910"), new String[] {"AC", "ABS", "Airbag"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        /* Модифицируем json-строку */
        final String carJson =
                "{"
                        + "\"transmission\":false,"
                        + "\"year\":2020,"
                        + "\"engine\":"
                        + "{"
                        + "\"vincode\":\"WVWZZZ3CZLE010298\""
                        + "},"
                        + "\"equipment\":"
                        + "[\"AC\",\"ABS\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}