package ru.job4j.gc.leak;

import java.util.List;
import java.util.Objects;

public class Post {

    private int id;

    private String text;

    private List<Comment> comments;

    public Post(int id, String text, List<Comment> comments) {
        this.id = id;
        this.text = text;
        this.comments = comments;
    }

    public Post(String text, List<Comment> comments) {
        this.text = text;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post post)) {
            return false;
        }
        return Objects.equals(getText(), post.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getText());
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", text='" + text + '\''
                + ", comments=" + comments
                + '}';
    }
}