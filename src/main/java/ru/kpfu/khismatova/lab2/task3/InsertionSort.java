package ru.kpfu.khismatova.lab2.task3;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 2, -1, 4, 3, 7, 5, 3, 0, -2};

        insertionSort(array);

        System.out.println(Arrays.toString(array));
    }

    // Метод сортировки вставками
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i]; // Текущий элемент для вставки
            int j = i - 1;
            // Перемещаем элементы, которые больше ключа, на одну позицию вперед
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            // Вставляем ключ на его правильное место
            array[j + 1] = key;
        }
    }

}
