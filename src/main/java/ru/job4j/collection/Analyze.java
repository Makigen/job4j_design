package ru.job4j.collection;

import java.util.*;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();

        Map<Integer, User> currentMap = current.stream().collect(Collectors.toMap(User::getId, User -> User));

        for (User user : previous) {
            if (!currentMap.containsKey(user.getId())) {
                rsl.deleted++;
            }
        }

        for (Map.Entry<Integer, User> entry : currentMap.entrySet()) {
            if (!previous.contains(entry.getValue())) {
                rsl.added++;
            }
        }

        for (Map.Entry<Integer, User> entry : currentMap.entrySet()) {
            for (User user : previous) {
                if (entry.getKey() == user.getId() && !entry.getValue().getName().equals(user.getName())) {
                    rsl.changed++;
                }
            }
        }
        return rsl;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added && changed == info.changed && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{" + "added = " + added + ", changed = " + changed + ", deleted = " + deleted + '}';
        }
    }
}