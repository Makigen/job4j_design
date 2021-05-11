package ru.job4j.collection;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import ru.job4j.collection.Analyze.Info;

import java.util.Arrays;
import java.util.List;

public class AnalyzeTest {

    @Test
    public void whenCurrentBiggerThanPrevious() {
        Analyze.User user1 = new Analyze.User(1, "Name1");
        Analyze.User user2 = new Analyze.User(2, "Name2");
        Analyze.User user3 = new Analyze.User(3, "Name3");
        Analyze.User user4 = new Analyze.User(4, "Name4");
        Analyze.User user5 = new Analyze.User(5, "Name5");

        List<Analyze.User> list1 = Arrays.asList(user1, user2, user3, user4);

        List<Analyze.User> list2 = Arrays.asList(user1, user2, user3, user4, user5);

        Info inf = Analyze.diff(list1, list2);
        assertThat(inf.added, is(1));
    }

    @Test
    public void whenEditedElement() {
        Analyze.User user1 = new Analyze.User(1, "Name1");
        Analyze.User user2 = new Analyze.User(2, "Name2");
        Analyze.User user3 = new Analyze.User(3, "Name3");
        Analyze.User user4 = new Analyze.User(4, "Name4");
        Analyze.User user5 = new Analyze.User(4, "Name5");
        Analyze.User user6 = new Analyze.User(3, "Name5");

        List<Analyze.User> list1 = Arrays.asList(user1, user2, user3, user4);
        List<Analyze.User> list2 = Arrays.asList(user1, user2, user6, user5);

        Info inf = Analyze.diff(list1, list2);
        assertThat(inf.changed, is(2));
    }

    @Test
    public void whenDeletedElements() {
        Analyze.User user1 = new Analyze.User(1, "Name1");
        Analyze.User user2 = new Analyze.User(2, "Name2");
        Analyze.User user3 = new Analyze.User(3, "Name3");
        Analyze.User user4 = new Analyze.User(4, "Name4");
        Analyze.User user5 = new Analyze.User(5, "Name5");

        List<Analyze.User> list1 = Arrays.asList(user1, user2, user3, user4);

        List<Analyze.User> list2 = Arrays.asList(user1, user2, user3);

        Info inf = Analyze.diff(list1, list2);
        assertThat(inf.deleted, is(1));
    }

    @Test
    public void whenWholeListChanged() {
        Analyze.User user1 = new Analyze.User(1, "Name1");
        Analyze.User user2 = new Analyze.User(2, "Name2");
        Analyze.User user3 = new Analyze.User(3, "Name3");
        Analyze.User user4 = new Analyze.User(4, "Name4");
        Analyze.User user5 = new Analyze.User(5, "Name5");
        Analyze.User user6 = new Analyze.User(3, "Name5");

        List<Analyze.User> list1 = Arrays.asList(user1, user2, user3);
        List<Analyze.User> list2 = Arrays.asList(user4, user5, user6);

        Info inf = Analyze.diff(list1, list2);

        Info rsl = new Info();
        rsl.added = 2;
        rsl.changed = 1;
        rsl.deleted = 2;

        assertThat(inf, is(rsl));
    }
}