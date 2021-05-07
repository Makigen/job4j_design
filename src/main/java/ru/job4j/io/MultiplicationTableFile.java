package ru.job4j.io;

import java.io.FileOutputStream;

public class MultiplicationTableFile {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s.append((i + 1) * (j + 1) + " ");
            }
            s.append("\n");
        }

        try (FileOutputStream out = new FileOutputStream("multiplication_table.txt")) {
            out.write(s.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



