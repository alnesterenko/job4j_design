package ru.job4j.ood.ocp;

/**
 * Из за модификатора final,
 * при попытке: public class MaybeNotFinal extends FinalClass { };,
 *  в IntellijIdea сразу выскакивает ошибка, которая сообщает: "расширения, путём наследования, не будет!")))
 */

public final class FinalClass {

    private int param1;
    private int param2;

    public FinalClass(int param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public int getParam1() {
        return param1;
    }

    public int getParam2() {
        return param2;
    }
}
