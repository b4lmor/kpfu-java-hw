package ru.kpfu.khismatova.lab2.task3;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 2, -1, 4, 3, 7, 5, 3, 0, -2};

        selectionSort(array);

        System.out.println(Arrays.toString(array));
    }

    // Метод сортировки выбором
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            // Находим индекс минимального элемента в неотсортованной части массива
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Меняем местами найденный минимальный элемент с первым элементом неотсортованной части
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

}
