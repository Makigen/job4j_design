package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
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
            PrintStream fileOut = new PrintStream(path);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Chat Bot: Welcome to chat! ");
            fileOut.println("Chat Bot: Welcome to chat! ");
            String line = scanner.nextLine();
            fileOut.println("User: " + line);
            while (!line.equals(OUT)) {
                System.out.println("User: " + line);
                if (!pause(line)) {
                    String chatAnswer = botAnswer();
                    fileOut.println("Chat: " + chatAnswer);
                    System.out.println("Chat: " + chatAnswer);
                }
                line = scanner.nextLine();
                fileOut.println("User: " + line);
            }
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

    private String botAnswer() throws IOException {
        String line = "";
        List<String> array = Files.readAllLines(Path.of(botAnswers));
        Random rand = new Random();
        if (array.size() > 0) {
            int randomIndex = rand.nextInt(array.size() - 1);
            line = array.get(randomIndex);
        }
        return line;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatLog.txt", "./data/chatBotAnswers.txt");
        cc.run();
    }
}