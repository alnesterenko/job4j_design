package ru.job4j.ood.srp.comparator;

import ru.job4j.ood.srp.model.Employee;

import java.util.Comparator;

public class SortBySalaryReduction implements Comparator<Employee> {

    public int compare(Employee employee1, Employee employee2) {
        Double salary1 = employee1.getSalary();
        Double salary2 = employee2.getSalary();
        return salary2.compareTo(salary1);
    }
}
