package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] numbers = text.toString().split(System.lineSeparator());
        for (String number : numbers) {
            String rsl = "Нечётное число";
            if (Integer.valueOf(number) % 2 == 0) {
                rsl = "Чётное число";
            }
            System.out.println(number + " " + rsl);
        }
    }
}