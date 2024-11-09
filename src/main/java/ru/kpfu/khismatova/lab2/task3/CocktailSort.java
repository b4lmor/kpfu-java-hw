package ru.kpfu.khismatova.lab2.task3;

import java.util.Arrays;

public class CocktailSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 2, -1, 4, 3, 7, 5, 3, 0, -2};

        cocktailSort(array);

        System.out.println(Arrays.toString(array));
    }

    // Метод шейкерной сортировки
    public static void cocktailSort(int[] array) {
        boolean swapped = true;
        int start = 0;
        int end = array.length - 1;

        while (swapped) {
            swapped = false;

            // Проход слева направо
            for (int i = start; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    // Меняем местами элементы
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            // Если не было замен, массив уже отсортирован
            if (!swapped) {
                break;
            }

            // Уменьшаем конец, так как последний элемент уже на своем месте
            end--;

            swapped = false;

            // Проход справа налево
            for (int i = end; i > start; i--) {
                if (array[i] < array[i - 1]) {
                    // Меняем местами элементы
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    swapped = true;
                }
            }

            // Увеличиваем начало, так как первый элемент уже на своем месте
            start++;
        }
    }

}
