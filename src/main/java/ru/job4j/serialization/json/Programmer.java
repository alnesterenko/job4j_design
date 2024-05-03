package ru.job4j.serialization.json;

import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Objects;

@XmlRootElement(name = "programmer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Programmer {
    @XmlAttribute
    private boolean commercialDevelopmentExperience;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private String name;
    @XmlElement
    private  Experience experience;
    @XmlElementWrapper
    @XmlElement(name = "completedProject")
    private String[] completedProjects;

    public Programmer() {
    }

    public Programmer(boolean commercialDevelopmentExperience, int age,
                      String name, Experience experience, String[] completedProjects) {
        this.commercialDevelopmentExperience = commercialDevelopmentExperience;
        this.age = age;
        this.name = name;
        this.experience = experience;
        this.completedProjects = completedProjects;
    }

    public boolean isCommercialDevelopmentExperience() {
        return commercialDevelopmentExperience;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Experience getExperience() {
        return experience;
    }

    public String[] getCompletedProjects() {
        return completedProjects;
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

    public static void main(String[] args) throws JAXBException {
        final Programmer programmer = new Programmer(true, 41, "Mikel Jackson",
                new Experience("PHP", 2), new String[]{"Calories calculator", "JSON parser"});
        JAXBContext context = JAXBContext.newInstance(Programmer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(programmer, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
