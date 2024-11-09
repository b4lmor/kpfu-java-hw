package ru.kpfu.khismatova.lab2.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortTest {

    @Test
    void testQuickSort() {
        int[] input = {5, 3, 8, 6, -2};
        int[] expected = {-2, 3, 5, 6, 8};

        QuickSort.quickSort(input, 0, input.length - 1);
        assertArrayEquals(expected, input);
    }

    @Test
    void testBubbleSort() {
        int[] input = {5, 3, 8, 6, -2};
        int[] expected = {-2, 3, 5, 6, 8};

        BubbleSort.bubbleSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testSelectionSort() {
        int[] input = {5, 3, 8, 6, -2};
        int[] expected = {-2, 3, 5, 6, 8};

        SelectionSort.selectionSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testInsertionSort() {
        int[] input = {5, 3, 8, 6, -2};
        int[] expected = {-2, 3, 5, 6, 8};

        InsertionSort.insertionSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testCocktailSort() {
        int[] input = {5, 3, 8, 6, -2};
        int[] expected = {-2, 3, 5, 6, 8};

        CocktailSort.cocktailSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    void testHeapSort() {
        int[] input = {5, 3, 8, 6, -2};
        int[] expected = {-2, 3, 5, 6, 8};

        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

}
