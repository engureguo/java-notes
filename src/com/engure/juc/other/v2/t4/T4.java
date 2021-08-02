package com.engure.juc.other.v2.t4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

public class T4 {
    public static void main(String[] args) {
        User u1 = new User(1, 21, "a");
        User u2 = new User(2, 22, "b");
        User u3 = new User(3, 23, "c");
        User u4 = new User(4, 24, "d");
        User u5 = new User(5, 25, "e");

        ///////////////////

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
        list.stream()
                .filter(u->u.getId()%2==0)
                .filter(u->u.getAge()>23)
                .map(u->u.getName().toUpperCase())
                .sorted((o1, o2) -> o2.compareTo(o1)) // Comparator.reverseOrder()
                .limit(1)
                .forEach(System.out::println);

    }

    @Data
    @AllArgsConstructor
    private static class User {
        private Integer id;
        private Integer age;
        private String name;
    }
}
