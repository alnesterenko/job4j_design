package ru.job4j.gc.leak;

import java.util.Objects;

public class Comment {

    private String text;

    private User user;

    public Comment(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment comment)) {
            return false;
        }
        return Objects.equals(getText(), comment.getText()) && Objects.equals(getUser(), comment.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getText(), getUser());
    }

    @Override
    public String toString() {
        return "Comment{"
                + "text='" + text + '\''
                + ", user=" + user
                + '}';
    }
}