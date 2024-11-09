package ru.kpfu.khismatova.lab2.task2;

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        foo(array);

        System.out.println(Arrays.toString(array));
    }

    private static void foo(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int abs = Math.abs(array[i]);
            if (abs % 2 == 0) {
                array[i] = -abs;
            } else {
                array[i] = abs;
            }
        }
    }

}
