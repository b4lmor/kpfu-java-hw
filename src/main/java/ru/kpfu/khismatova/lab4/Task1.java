package ru.kpfu.khismatova.lab4;

enum Animal {
    CAT, DOG, COW, SHEEP, HORSE, TIGER
}

public class Task1 {
    public static void main(String[] args) {
        Animal myAnimal = Animal.CAT;

        System.out.println(getVoice(myAnimal));

        System.out.println("\nAnimals:");
        for (Animal animal : Animal.values()) {
            System.out.println(animal);
        }
    }

    private static String getVoice(Animal animal) {
        return switch (animal) {
            case CAT -> "Meow";
            case DOG -> "Bark";
            case COW -> "Moo";
            case SHEEP -> "Baa";
            case HORSE -> "Igo-go";
            case TIGER -> "Roar";
        };
    }
}
