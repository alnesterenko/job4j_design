package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema5D implements Cinema {

    private List<Session> allSessions; /* поле добавленно временно, чтобы можно было создать тест на метод add() */

    public List<Session> getAllSessions() { /* метод добавленно временно, в рамках создания теста на метод add() */
        return allSessions;
    }

    /**
     * @param filter
     * @return List<Session> (но в данный момент пустой список киносеансов)
     */
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return List.of();
    }

    /**
     * @param account
     * @param row
     * @param column
     * @param date
     * @return Ticket (но в данный момент просто null)
     */
    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    /**
     * @param session
     */
    @Override
    public void add(Session session) {

    }
}
