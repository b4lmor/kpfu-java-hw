package ru.kpfu.khismatova.lab1.task1.animal;

public abstract class Animal {
    public int pawCount;

    protected Animal(int p) {
        this.pawCount = p;
    }

    public abstract void say();
}
