package ru.kpfu.khismatova.lab3;

import ru.kpfu.khismatova.lab3.interpolation.Lagrange;
import ru.kpfu.khismatova.lab3.interpolation.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 {

    static Random random = new Random();

    public static void main(String[] args) {
        int start = 0;
        int end = 10;

        List<Point> tableF = new ArrayList<>();
        for (double x = start; x < end; x++) {
            tableF.add(new Point(x, f(x)));
        }
        var lagrange = new Lagrange().interpolate(tableF);
        for (int i = 0; i < 5; i++) {
            double x = random.nextDouble(start, end + 100);
            System.out.printf("%d. f(%f) = %f | L(%f) = %f\n", i, x, f(x), x, lagrange.apply(x));
        }
    }

    private static double f(double x) {
        return x * x;
    }

}
