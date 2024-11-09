package ru.kpfu.khismatova.lab1.task1.animal;

public class Cat extends Animal {
    public Cat() {
        super(4);
    }

    @Override
    public void say() {
        System.out.println("hi from a cat!");
    }
}
