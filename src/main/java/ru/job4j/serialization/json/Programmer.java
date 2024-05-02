package ru.job4j.serialization.json;

import java.util.Arrays;
import java.util.Objects;

public class Programmer {

    private final boolean commercialDevelopmentExperience;

    private final int age;

    private final String name;

    private  final Experience experience;

    private final String[] completedProjects;

    public Programmer(boolean commercialDevelopmentExperience, int age,
                      String name, Experience experience, String[] completedProjects) {
        this.commercialDevelopmentExperience = commercialDevelopmentExperience;
        this.age = age;
        this.name = name;
        this.experience = experience;
        this.completedProjects = completedProjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Programmer that)) {
            return false;
        }
        return commercialDevelopmentExperience == that.commercialDevelopmentExperience && age == that.age && Objects.equals(name, that.name) && Objects.equals(experience, that.experience) && Objects.deepEquals(completedProjects, that.completedProjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commercialDevelopmentExperience, age, name, experience, Arrays.hashCode(completedProjects));
    }

    @Override
    public String toString() {
        return "Programmer{"
                + "commercialDevelopmentExperience=" + commercialDevelopmentExperience
                + ", age=" + age
                + ", name='" + name + '\''
                + ", experience=" + experience
                + ", completedProjects=" + Arrays.toString(completedProjects)
                + '}';
    }
}
