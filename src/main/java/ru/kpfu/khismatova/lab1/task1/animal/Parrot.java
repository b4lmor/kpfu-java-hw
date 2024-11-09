package ru.kpfu.khismatova.lab1.task1.animal;

public class Parrot extends Animal {

    public Parrot() {
        super(2);
    }

    @Override
    public void say() {
        System.out.println("hi from a parrot!");
    }

}
