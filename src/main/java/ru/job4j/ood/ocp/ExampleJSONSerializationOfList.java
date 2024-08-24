package ru.job4j.ood.ocp;

import com.google.gson.GsonBuilder;

import java.util.List;

public class ExampleJSONSerializationOfList {
    public static void main(String[] args) {
        var users = List.of(
                new User("123", "456"),
                new User("abc", "xyz"),
                new User("job4j", "junior")
        );
        var library = new GsonBuilder().create();
        System.out.println(library.toJson(users));
    }

    public static class User {

        private String name;

        private String lastName;

        public User(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
    }
}
