package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InJSONAndBack {
    public static void main(String[] args) {
        final Programmer programmer = new Programmer(true, 36, "John Doe",
                new Experience("Java", 1), new String[]{"Calculator", "Fitness program"});
        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(programmer);
        System.out.println(json);
        final Programmer programmerFromJSON = gson.fromJson(json, Programmer.class);
        System.out.println(programmerFromJSON);
    }
}
