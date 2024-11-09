package ru.kpfu.khismatova.lab2.task3;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] array = {1, 6, 2, -1, 4, 3, 7, 5, 3, 0, -2};

        heapSort(array);

        System.out.println(Arrays.toString(array));
    }

    // Метод сортировки пирамидальной сортировкой
    public static void heapSort(int[] array) {
        int n = array.length;

        // Построение кучи (перегруппировка массива)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Извлечение элементов из кучи
        for (int i = n - 1; i > 0; i--) {
            // Перемещение текущего корня в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызов heapify на уменьшенной куче
            heapify(array, i, 0);
        }
    }

    // Метод для преобразования подмассива в кучу
    private static void heapify(int[] array, int n, int i) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int left = 2 * i + 1; // Левый дочерний элемент
        int right = 2 * i + 2; // Правый дочерний элемент

        // Если левый дочерний элемент больше корня
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // Если правый дочерний элемент больше наибольшего элемента
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // Если наибольший элемент не корень
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Рекурсивно преобразуем затронутое поддерево в кучу
            heapify(array, n, largest);
        }
    }

}
