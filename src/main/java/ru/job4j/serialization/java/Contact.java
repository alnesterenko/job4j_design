package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact contact)) {
            return false;
        }
        return getZipCode() == contact.getZipCode() && Objects.equals(getPhone(), contact.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getZipCode(), getPhone());
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        System.out.printf("Before serialization: %s %s", contact, System.lineSeparator());
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject();
            System.out.printf("After serialization: %s %s", contactFromFile, System.lineSeparator());
            System.out.printf("Объекты до сериализации и после равны: %b", contact.equals(contactFromFile));
        }
    }
}