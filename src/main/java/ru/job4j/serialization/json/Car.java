package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isTransmission() {
        return transmission;
    }

    public int getYear() {
        return year;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getEquipment() {
        return equipment;
    }

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonEngine = new JSONObject("{\"vincode\":\"WVWZZZ3CZLE028910\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("AC");
        list.add("ABS");
        list.add("Airbag");
        JSONArray jsonEquipments = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car(true, 2021, new Engine("WVWZZZ3CZLE028910"), new String[] {"AC", "ABS", "Airbag"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("transmission", car.isTransmission());
        jsonObject.put("year", car.getYear());
        jsonObject.put("engine", jsonEngine);
        jsonObject.put("equipments", jsonEquipments);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}