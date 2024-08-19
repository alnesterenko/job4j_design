package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
public class Cinema5DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema5D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket5D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema5D();
        Session session = new Session5D();
        cinema.add(session);
        List<Session> sessions = cinema.find(data -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema5D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /* Ниже идут добавленные мной тесты */

    @Test
    public void whenAdd1AndGet1() {
        Cinema5D cinema = new Cinema5D();
        cinema.add(new Session5D());
        int countOfSessions = cinema.getAllSessions().size();
        assertThat(countOfSessions).isEqualTo(1);
    }

    @Test
    public void whenTryingToFindInEmptyAllSessions() {
        Cinema5D cinema = new Cinema5D();
        List<Session> sessions = cinema.find(data -> true);
        int countOfSessions = cinema.getAllSessions().size();
        assertThat(countOfSessions).isEqualTo(0);
        assertThat(sessions).isEmpty();
    }
}