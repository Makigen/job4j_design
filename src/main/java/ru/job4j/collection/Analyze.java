package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Analyze {

    public static Info diff(List<User> previous, List<User> current) {
        Info rsl = new Info();
        List<Analyze.User> deleted = new ArrayList<>(previous);
        List<Analyze.User> added = new ArrayList<>(current);

        current.forEach(deleted::remove);
        previous.forEach(added::remove);

        rsl.deleted = deleted.size();
        rsl.added = added.size();

        for (User userPrevious : previous) {
            for (User userCurrent : current) {
                if (userCurrent.id == userPrevious.id && !userCurrent.name.equals(userPrevious.name)) {
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