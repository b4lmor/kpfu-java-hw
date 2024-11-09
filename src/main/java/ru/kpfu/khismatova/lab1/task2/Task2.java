package ru.kpfu.khismatova.lab1.task2;

public class Task2 {

    public static void main(String[] args) {
        var t = new Task2();
        t.printHiNTimes(5);
        int k = t.sum(1, 2);
        System.out.println(k);
    }

    public void printHiNTimes(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Hi");
        }
    }

    public int sum(int a, int b) {
        int s;
        s = a + b;
        return s;
    }
}
