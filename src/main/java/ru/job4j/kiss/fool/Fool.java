package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    private static int startAt = 1;
    private static Scanner input = new Scanner(System.in);

    public static void setStartAt(int startAt) {
        Fool.startAt = startAt;
    }

    public static String check() {
        StringBuilder result = new StringBuilder();
        if (startAt % 3 == 0) {
            result.append("Fizz");
        }
        if (startAt % 5 == 0) {
            result.append("Buzz");
        }
        return result.toString();
    }

    private static void move() {
        if (startAt % 2 == 1) {
            computerMove();
        } else {
            playerMove();
        }
        startAt++;
    }

    private static void computerMove() {
        String checkResult = check();
        if (!checkResult.isEmpty()) {
            System.out.println(checkResult);
        } else {
            System.out.println(startAt);
        }
    }

    private static void playerMove() {
        String answer = input.nextLine();
        String checkResult = check();
        if ((!checkResult.isEmpty() && !checkResult.equals(answer)) || (checkResult.isEmpty() && !String.valueOf(startAt).equals(answer))) {
            System.out.println("Ошибка. Начинай снова.");
            startAt = 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        while (startAt < 100) {
            move();
        }
        input.close();
    }
}
