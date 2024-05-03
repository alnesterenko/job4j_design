package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "experience")
public class Experience {
    @XmlAttribute
    private String language;
    @XmlAttribute
    private int timeOfUsage;

    public Experience() {
    }

    public Experience(String language, int timeOfUsage) {
        this.language = language;
        this.timeOfUsage = timeOfUsage;
    }

    public String getLanguage() {
        return language;
    }

    public int getTimeOfUsage() {
        return timeOfUsage;
    }

    @Override
    public String toString() {
        return "Experience{"
                + "language='" + language + '\''
                + ", timeOfUsage=" + timeOfUsage
                + '}';
    }
}
