package ru.job4j.io.search;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Find {

    public static void main(String[] args) throws IOException {
        ArgsName jvm = ArgsName.of(args);
        Path start = Paths.get(jvm.get("d"));
        Predicate<Path> condition = getSearchType(jvm.get("t"), jvm.get("n"));
        List<String> log = new ArrayList<>();
        search(start, condition).forEach(p -> log.add(p.toString()));
        log.forEach(System.out::println);
        try (PrintStream fileOut = new PrintStream(jvm.get("o"))) {
            log.forEach(fileOut::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        Find.SearchFiles searcher = new Find.SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static class ArgsName {

        private final Map<String, String> values = new HashMap<>();

        public String get(String key) {
            return values.get(key);
        }

        private void parse(String[] args) {
            if (args.length < 4) {
                throw new IllegalArgumentException("Usage java -jar find.jar -d=ROOT_FOLDER -n=KEY -t=SEARCH_TYPE(name | mask | regex) -o=LOG_FILE");
            }
            for (String s : args) {
                String key = s.substring(s.indexOf("-") + 1, s.indexOf("="));
                String[] split = s.split("=");
                if (split.length < 2) {
                    throw new IllegalArgumentException("Usage java -jar find.jar -d=ROOT_FOLDER -n=KEY -t=SEARCH_TYPE(name | mask | regex) -o=LOG_FILE");
                }
                String value = split[1];
                values.put(key, value);
            }
        }

        public static ArgsName of(String[] args) {
            ArgsName names = new ArgsName();
            names.parse(args);
            return names;
        }
    }

    private static class SearchFiles implements FileVisitor<Path> {

        Predicate<Path> condition;
        private List<Path> paths = new ArrayList<>();

        public List<Path> getPaths() {
            return paths;
        }

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (condition.test(file.toAbsolutePath())) {
                paths.add(file.toAbsolutePath());
            }
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return CONTINUE;
        }
    }

    private static String maskToRegex(String mask) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char symbol = mask.charAt(i);
            switch (symbol) {
                case '*':
                    stringBuilder.append(".*");
                    break;
                case '.':
                    stringBuilder.append("\\.");
                    break;
                default:
                    stringBuilder.append(symbol);
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private static Predicate<Path> getSearchType(String t, String n) {
        Predicate<Path> condition;
        switch (t) {
            case "mask":
                condition = p -> Pattern.compile(maskToRegex(n)).matcher(p.toFile().getName()).find();
                break;
            case "regex":
                condition = p -> Pattern.compile(n).matcher(p.toFile().getName()).find();
                break;
            default:
                condition = p -> p.toFile().getName().equals(n);
                break;
        }
        return condition;
    }
}