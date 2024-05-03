package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLMain {
    public static void main(String[] args) throws Exception {
        Programmer programmer = new Programmer(true, 36, "John Doe",
                new Experience("Java", 1), new String[]{"Calculator", "Fitness program"});
        JAXBContext context = JAXBContext.newInstance(Programmer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(programmer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Programmer result = (Programmer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}