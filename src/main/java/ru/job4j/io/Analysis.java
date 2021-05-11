package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                    time = begin + ";" + line.split(" ")[1] + ";";
                    log.add(time);
                    begin = "";
                }
                line = read.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
