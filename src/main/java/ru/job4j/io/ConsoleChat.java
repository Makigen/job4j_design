package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private boolean pause;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try {
            List<String> log = new ArrayList<>();
            List<String> listOfAnswers = botAnswer();
            String chatAnswer = "";
            Random rand = new Random();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Chat Bot: Welcome to chat! ");
            log.add("Chat Bot: Welcome to chat! ");
            String line = scanner.nextLine();
            log.add("User: " + line);
            while (!line.equals(OUT)) {
                if (listOfAnswers.size() > 0) {
                    int randomIndex = rand.nextInt(listOfAnswers.size() - 1);
                    chatAnswer = listOfAnswers.get(randomIndex);
                }
                System.out.println("User: " + line);
                if (!pause(line)) {
                    log.add("Chat: " + chatAnswer);
                    System.out.println("Chat: " + chatAnswer);
                }
                line = scanner.nextLine();
                log.add("User: " + line);
            }
            writeListToFile(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean pause(String line) {
        switch (line) {
            case (STOP):
                pause = true;
                break;
            case (CONTINUE):
                pause = false;
            default:
        }
        return pause;
    }

    private List<String> botAnswer() throws IOException {
        return Files.readAllLines(Path.of(botAnswers));
    }

    private void writeListToFile(List<String> log) {
        try {
            PrintStream fileOut = new PrintStream(path);
            log.forEach(fileOut::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatLog.txt", "./data/chatBotAnswers.txt");
        cc.run();
    }
}