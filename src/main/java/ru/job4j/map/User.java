package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User user)) {
            return false;
        }
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        HashMap<User, Object> map = new HashMap<>(16);
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("Alex", 21, birthday);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int backet1 = hash1 & 15;
        map.put(user1, new Object());
        System.out.printf("user1 - хешкод: %s, хеш: %s, бакет: %s", hashCode1, hash1, backet1);
        System.out.println();
        User user2 = new User("Alex", 21, birthday);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int backet2 = hash2 & 15;
        map.put(user2, new Object());
        System.out.printf("user2 - хешкод: %s, хеш: %s, бакет: %s", hashCode2, hash2, backet2);
        System.out.println();
    }
}
