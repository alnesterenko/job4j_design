package ru.job4j.clone;

/* Глубокое копирование (Deep copy) */

public class TestObjectWithInnerObject implements Cloneable {
    int num;
    InnerObject innerObj;

    @Override
    protected TestObjectWithInnerObject clone() throws CloneNotSupportedException {
        TestObjectWithInnerObject testObj = (TestObjectWithInnerObject) super.clone();
        testObj.innerObj = innerObj.clone();
        return testObj;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObjectWithInnerObject testObj1 = new TestObjectWithInnerObject();
        testObj1.num = 5;
        InnerObject innerObj = new InnerObject();
        innerObj.num = 15;
        testObj1.innerObj = innerObj;
        TestObjectWithInnerObject testObj2 = testObj1.clone();
        testObj2.num = 25;
        testObj2.innerObj.num = 35;
        System.out.println("Исходный класс: " + testObj1.num);
        System.out.println("Исходный класс: " + testObj1.innerObj.num);
        System.out.println("Скопированный класс: " + testObj2.num);
        System.out.println("Скопированный класс: " + testObj2.innerObj.num);
    }
}