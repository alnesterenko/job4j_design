package ru.job4j.ood.srp;

/**
 * Этот класс как швейчарский нож -- хранит в себе модель и выполняет с ней целую кучу разных действий:
 * сохраняет, отправляет(по сети), распечатывает и логирует.
 * Это нарушает принцип SRP. У класса может быть только одна причина для изменения.
 * То есть, например, если мне понадобиться сохранять объект этого класса не в базу данных, а в файл,
 *  то мне придётся лезть в этот класс.
 *  А если мне нужно будет отпавлять объект этого класса не посети(через HTTP), а по почте, то опять для изменения метода
 *  мне придётся лезть в этот класс.
 *
 *  Чтобы исправить это "недоразумение" нужно разделить класс на части:
 *  - создать отдельный класс-модель, который умеет только создавать класс.
 *    И имеет конструктор, геттеры-сеттеры и другие "классические" методы.
 *  - создать отдельный класс Repository, который будет отвечать только за
 *    сохранение, получение(из хранилища) и удаление(из хранилища).
 *  - класс Sender, отвечающий за отправку.
 *  - Logger, отвечающий за логирование.
 *  - Printer, отвечающий за вывод(печать).
 *
 *  Вот тогда мы не будем "бегать" в огромный класс по каждой мелочи. И к тому же получившиеся мелкие классы
 *  будет удобно переиспользовать в другой конструкции.
 */
public class SomeClass {
    private int field1;
    private int field2;

    public int getField1() {
        return field1;
    }

    public int getField2() {
        return field2;
    }

    public boolean save() {
        return true;
    }

    public boolean send() {
        return true;
    }

    public void print() {

    }

    public void log() {

    }
 }
