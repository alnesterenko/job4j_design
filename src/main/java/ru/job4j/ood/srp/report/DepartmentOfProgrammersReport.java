package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class DepartmentOfProgrammersReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimiter;

    public DepartmentOfProgrammersReport(Store store, DateTimeParser<Calendar> dateTimeParser, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(delimiter)
                    .append(dateTimeParser.parse(employee.getHired())).append(delimiter)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimiter)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
