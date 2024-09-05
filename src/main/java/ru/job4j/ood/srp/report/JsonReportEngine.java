package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.adapter.JsonCalendarAdapter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> listOfEmployees = store.findBy(filter);
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Calendar.class, new JsonCalendarAdapter());
        builder.registerTypeAdapter(GregorianCalendar.class, new JsonCalendarAdapter());
        builder.setPrettyPrinting();
        final Gson gson = builder.create();
        return gson.toJson(listOfEmployees);
    }
}
