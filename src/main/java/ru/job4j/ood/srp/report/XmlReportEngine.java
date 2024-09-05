package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private final Store store;
    private final SimpleDateFormat formater = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public XmlReportEngine(Store store) {
        this.store = store;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
