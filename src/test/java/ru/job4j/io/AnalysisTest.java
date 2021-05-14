package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalysisTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void serverUnavailableAnalysis() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01\n" +
                    "\n" +
                    "200 10:57:01\n" +
                    "\n" +
                    "400 10:58:01\n" +
                    "\n" +
                    "200 10:59:01\n" +
                    "\n" +
                    "500 11:01:02\n" +
                    "\n" +
                    "200 11:02:02\n" +
                    "\n" +
                    "200 11:05:02\n" +
                    "\n" +
                    "400 11:10:01\n" +
                    "\n" +
                    "500 11:15:05\n" +
                    "\n" +
                    "300 12:12:12");
        }
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat(result.toString(),
                is("10:58:01;10:59:01;11:01:02;11:02:02;11:10:01;12:12:12;"));
    }
}