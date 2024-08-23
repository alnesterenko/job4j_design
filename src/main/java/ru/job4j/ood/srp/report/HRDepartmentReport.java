package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.comparator.SortBySalaryReduction;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Calendar;
import java.util.function.Predicate;

public class HRDepartmentReport implements Report {
    private final Store store;
    private final Comparator comparator;

    public HRDepartmentReport(Store store, Comparator comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> foundEmployees = store.findBy(filter);
        Collections.sort(foundEmployees, comparator);
        for (Employee employee : foundEmployees) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
