package ru.job4j.serialization.json;

public class Experience {

    private final String language;

    private final int timeOfUsage;

    public Experience(String language, int timeOfUsage) {
        this.language = language;
        this.timeOfUsage = timeOfUsage;
    }


    @Override
    public String toString() {
        return "Experience{"
                + "language='" + language + '\''
                + ", timeOfUsage=" + timeOfUsage
                + '}';
    }
    /* Специальный комментарий, чтобы этот файл можно было повторно закоммитить, добавив его в коммит к XML-файлу. */
}
