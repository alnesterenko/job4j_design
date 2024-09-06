package ru.job4j.ood.lsp;

/**
 *  Здесь класс-наследник(Square) не переопределяет методы класса-родителя(Rectangle)
 *  и как результат: метод getA() работает не правильно -- не возвращает настоящее значение поля а объекта класса Square
 *  и к тому же работает метод getB(), хотя у класса Square вообще нет поля b.
 */

public class Rectangle {
    private int a;
    private int b;

    public Rectangle(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Rectangle() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}

class Square extends Rectangle {
    private int a;

    public Square(int a) {
        this.a = a;
    }
}

class ClassForCheck {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(5, 10);
        System.out.println(rectangle.getA());
        System.out.println(rectangle.getB());
        Square square = new Square(15);
        System.out.println(square.getA());
        System.out.println(square.getB());
    }
}
