package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        String begin = "";
        String time = "";
        List<String> log = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String line = read.readLine();
            while (line != null) {
                if ((line.startsWith("400") || line.startsWith("500")) && begin.equals("")) {
                    begin = line.split(" ")[1];
                }
                if ((line.startsWith("200") || line.startsWith("300")) && !begin.equals("")) {
                    StringJoiner stringJoiner = new StringJoiner(";", "", ";");
                    time = stringJoiner.add(begin).toString();
                    time = stringJoiner.add(line.split(" ")[1]).toString();
                    log.add(time);
                    begin = "";
                }
                line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveToFile(log, target);
    }

    private void saveToFile(List<String> log, String target) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(target)))) {
            for (String string : log) {
                out.printf("%s%n", string);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.txt", "target.txt");
    }
}