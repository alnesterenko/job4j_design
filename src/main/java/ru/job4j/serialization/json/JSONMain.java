package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONMain {
    public static void main(String[] args) {
        Programmer programmer = new Programmer(true, 36, "John Doe",
                new Experience("Java", 1), new String[]{"Calculator", "Fitness program"});
        JSONObject jsonExperience = new JSONObject("{\"language\":\"Java\", \"timeOfUsage\":1}");
        JSONArray completedProjects = new JSONArray(new String[]{"Calculator", "Fitness program"});
        JSONObject jsonObjectFromString = new JSONObject();
        jsonObjectFromString.put("commercialDevelopmentExperience", programmer.isCommercialDevelopmentExperience());
        jsonObjectFromString.put("age", programmer.getAge());
        jsonObjectFromString.put("name", programmer.getName());
        jsonObjectFromString.put("experience", jsonExperience);
        jsonObjectFromString.put("completedProjects", completedProjects);
        System.out.println(jsonObjectFromString);
        JSONObject jsonObjectFromObject = new JSONObject(programmer);
        System.out.println(jsonObjectFromObject);
        System.out.println(jsonObjectFromString.toString().equals(jsonObjectFromObject.toString()));
    }
}
