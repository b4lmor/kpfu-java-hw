package ru.kpfu.khismatova.lab1.task1;

import ru.kpfu.khismatova.lab1.task1.animal.Animal;
import ru.kpfu.khismatova.lab1.task1.animal.Cat;
import ru.kpfu.khismatova.lab1.task1.animal.Parrot;

public class Task1 {

    public static void main(String[] args) {
        Animal cat = new Cat();
        System.out.println("Cat paw count: " + cat.pawCount);
        cat.say();

        Animal parrot = new Parrot();
        System.out.println("Parrot paw count: " + parrot.pawCount);
        parrot.say();
    }
}
