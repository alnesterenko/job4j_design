/**
 * Здесь интерфейс Developer(наследник) расширяет интерфейс Person(родитель)
 *  и реализующим его классам(Frontend, Backend, ProjectManager) даёт возможность писать код.
 *  Но, ProjectManager-у не нужно писать код.
 *  !!! Здесь, по-хорошему, нужно:
 *  1) "разорвать" наследование между Developer и Person.
 *  2) Из интерфейса Developer убрать методы eat() и think().
 *  3) ProjectManager должен реализовывать только интерфейс Person.
 *  (Хотя можно обойтись только третим пунктом)))))
 */

package ru.job4j.ood.isp;

public interface Person {
    void eat();
    void think();
}

interface Developer extends Person {
    void eat();
    void think();
    String writeCode();
}

class Frontend implements Developer {
    @Override
    public void eat() {

    }

    @Override
    public void think() {

    }

    @Override
    public String writeCode() {
        return "code";
    }

    private void useBrowserDevTools() {

    }
}

class Backend implements Developer {
    @Override
    public void eat() {

    }

    @Override
    public void think() {

    }

    @Override
    public String writeCode() {
        return "code";
    }


    private void configureDatabase() {

    }
}

class ProjectManager implements Developer {
    @Override
    public void eat() {

    }

    @Override
    public void think() {

    }

    /**
     * Манагер не умеет писать код
     * @return null -- при попытке писать код, он выдаёт "ничто".
     */
    @Override
    public String writeCode() {
        return null;
    }

    /**
     * Зато он умеет рулить командой.
     */
    public void manageTeam() {

    }
}
