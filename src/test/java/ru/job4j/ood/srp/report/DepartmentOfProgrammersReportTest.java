package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentOfProgrammersReportTest {

    @Test
    public void whenDelimiterIsSemicolon() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        String delimiter = ";";
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        Report report = new DepartmentOfProgrammersReport(store, parser, delimiter);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(delimiter)
                .append(parser.parse(worker1.getHired())).append(delimiter)
                .append(parser.parse(worker1.getFired())).append(delimiter)
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenDelimiterIsColon() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        String delimiter = ":";
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        Report report = new DepartmentOfProgrammersReport(store, parser, delimiter);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(delimiter)
                .append(parser.parse(worker1.getHired())).append(delimiter)
                .append(parser.parse(worker1.getFired())).append(delimiter)
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }
}