package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class FinanceDepartmentReportTest {

    @Test
    public void whenConvertUSDToEUR() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Currency convertFrom = Currency.USD;
        Currency convertTo = Currency.EUR;
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        Report report = new FinanceDepartmentReport(store, parser, convertFrom, convertTo);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(parser.parse(worker1.getHired())).append(" ")
                .append(parser.parse(worker1.getFired())).append(" ")
                .append(converter.convert(convertFrom, worker1.getSalary(), convertTo))
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenConvertUSDToRUB() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Currency convertFrom = Currency.USD;
        Currency convertTo = Currency.RUB;
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        Report report = new FinanceDepartmentReport(store, parser, convertFrom, convertTo);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(parser.parse(worker1.getHired())).append(" ")
                .append(parser.parse(worker1.getFired())).append(" ")
                .append(converter.convert(convertFrom, worker1.getSalary(), convertTo))
                .append(System.lineSeparator());
        assertThat(report.generate(employee -> true)).isEqualTo(expected.toString());
    }
}