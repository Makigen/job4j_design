package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {
    @Test
    public void serverUnavailableAnalysis() {
        Analysis analysis = new Analysis();
        analysis.unavailable("server.txt", "target.txt");
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("target.txt"))) {
            reader.lines().forEach(result::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result.toString(),
                is("10:58:01;10:59:01;11:01:02;11:02:02;11:10:01;12:12:12;"));
    }
}
