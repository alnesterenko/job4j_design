package ru.job4j.ood.ocp;

/**
 * Короткое описание проблемы: создаётся персонаж, ему в руки вкладывается оружие, но каждый раз,
 *  когда нужно создать новое оружие, приходится изменять метод attack() класса Weapon.
 *
 *  Я думаю, что лучше было бы Weapon сделать интерфейсом,
 *   а каждое новое оружие новым классом, реализующим этот интерфес по-своему.
  */

public class Character {
    private String name;
    private Weapon weapon;

    public Character(String name, Weapon weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    public void changeWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void attack() {
        weapon.attack();
    }

    /**
     * После запуска этого метода, увидим:
     * "Удар мечём с уроном 15
     * Удар мечём с уроном 40"
     * То есть урон поменялся а название оружия нет.
     */
    public static void main(String[] args) {
        Weapon sword = new Weapon("sword", 15, 2);
        Character character = new Character("Слюнтяй", sword);
        character.attack();
        Weapon crossbow = new Weapon("crossbow", 40, 100);
        character.changeWeapon(crossbow);
        character.attack();
    }

    private static class Weapon {
        String type;
        int damage;
        int range;

        public Weapon(String type, int damage, int range) {
            this.type = type;
            this.damage = damage;
            this.range = range;
        }

        /**
         * Чтобы можно было добавлять новые виды оружия,
         * придётся каждый раз лазить в метод attack()
         * и увеличивать цепочку if-else или добавлять ещё один case в switch.
         */
        public void attack() {
            System.out.println("Удар мечём с уроном " + damage);
        }
    }
}
