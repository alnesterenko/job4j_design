package ru.job4j.ood.ocp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class ExampleXMLSerializationOfList {

    public static void main(String[] args) throws JAXBException {
        var users = new ArrayList<>(List.of(
                new User("123", "456"),
                new User("abc", "xyz"),
                new User("job4j", "junior")
        ));
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Users(users), writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class User {

        private String name;

        private String lastName;

        public User(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
    }

    @XmlRootElement(name = "users")
    public static class Users {

        private List<User> users;

        public Users() { }

        public Users(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }
}
