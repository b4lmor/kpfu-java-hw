package ru.kpfu.khismatova.lab2.task3;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 2, -1, 4, 3, 7, 5, 3, 0, -2};

        bubbleSort(array);

        System.out.println(Arrays.toString(array));
    }

    // Метод пузырьковой сортировки
    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        // Проходим по всем элементам массива
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Флаг для отслеживания, произошла ли замена

            // Последние i элементов уже отсортированы
            for (int j = 0; j < n - 1 - i; j++) {
                // Сравниваем соседние элементы
                if (array[j] > array[j + 1]) {
                    // Меняем их местами
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true; // Устанавливаем флаг, если произошла замена
                }
            }

            // Если за проход не было замен, массив уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }

}
