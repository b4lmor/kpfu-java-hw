package ru.kpfu.khismatova.lab2.task1;

import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        int[][] matrix = inputMatrix();
//        int[][] matrix = { {1, 1, 1}, {1, 7, 1} };

        int target = 7;

        Point position = findElement(target, matrix);

        if (position != null) {
            System.out.printf("Число %d найдено на позиции:\n\t- строка %d\n\t- столбец %d\n", target, position.y, position.x);
        } else {
            System.out.printf("Число %d не найдено.\n", target);
        }
    }

    private static Point findElement(int target, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private static int[][] inputMatrix() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк массива: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов массива: ");
        int cols = scanner.nextInt();

        int[][] array = new int[rows][cols];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        return array;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

}
