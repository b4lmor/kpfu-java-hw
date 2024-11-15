package ru.kpfu.khismatova.lab3.interpolation;

import java.util.List;
import java.util.function.Function;

public class Lagrange implements InterpolationFunction {

    @Override
    public Function<Double, Double> interpolate(List<Point> table) {
        return x -> {
            double s = 0;
            int n = table.size();
            for (int i = 0; i < n; i++) {
                s += table.get(i).y * getL(x, i, table);
            }
            return s;
        };
    }

    private double getL(double x, int index, List<Point> table) {
        double p = 1;
        int n = table.size();
        double x_i = table.get(index).x;
        for (int j = 0; j < n; j++) {
            if (index == j) {
                continue;
            }
            double x_j = table.get(j).x;
            p *= (x - x_j) / (x_i - x_j);
        }
        return p;
    }

}
