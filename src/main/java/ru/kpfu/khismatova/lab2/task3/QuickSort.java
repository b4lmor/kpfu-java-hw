package ru.kpfu.khismatova.lab2.task3;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 2, -1, 4, 3, 7, 5, 3, 0, -2};

        quickSort(array, 0, array.length - 1);

        System.out.println(Arrays.toString(array));
    }

    // Метод быстрой сортировки
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Разделяем массив и получаем индекс опорного элемента
            int pi = partition(array, low, high);

            // Рекурсивно сортируем элементы до и после опорного элемента
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    // Метод для разделения массива
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Опорный элемент
        int i = (low - 1); // индекс меньшего элемента

        for (int j = low; j < high; j++) {
            // Если текущий элемент меньше или равен опорному
            if (array[j] <= pivot) {
                i++;

                // Меняем местами элементы
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Меняем местами опорный элемент с элементом, следующим за меньшими
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; // Возвращаем индекс опорного элемента
    }

}
