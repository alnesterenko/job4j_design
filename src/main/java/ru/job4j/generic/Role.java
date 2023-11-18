package ru.job4j.generic;

public class Role extends Base {

    private final String nameOfTheRole;

    public Role(String id, String name) {
        super(id);
        this.nameOfTheRole = name;
    }

    public String getRoleName() {
        return nameOfTheRole;
    }
}
