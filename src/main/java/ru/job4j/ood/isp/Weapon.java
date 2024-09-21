/**
 * Интерфейс Weapon реализуют три вида оружия.
 * Но есть один нюанс: классу Knife нечего перезаряжать.
 * Поэтому, следовало бы:
 * 1) Разделить интерфейс Weapon на две части:
 *    интерфейс Atacker -- для видов оружия, которые могут атаковать
 *    и интерфейс Reloader -- для видов оружия, которые нужно перезаряжать.
 */

package ru.job4j.ood.isp;

public interface Weapon {
    void atack();
    String reload();
}

class Pistolet implements Weapon {
    @Override
    public void atack() {

    }

    @Override
    public String reload() {
        return "Оружие перезаряжено!";
    }
}

class RPG7D implements Weapon {
    @Override
    public void atack() {

    }

    @Override
    public String reload() {
        return "Оружие перезаряжено!";
    }
}

class Knife implements Weapon {
    @Override
    public void atack() {

    }

    @Override
    public String reload() {
        System.out.println("Нечего перезаряжать! Хотя, может следует наточить. ))))");
        return null;
    }
}
