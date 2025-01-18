package ru.kpfu.khismatova.lab5.math;

import ru.kpfu.khismatova.lab3.interpolation.Point;

import java.util.List;

public class Lagrange extends Polynomial implements Interpolation {


     // Он суммирует l_i * y_i для каждой точки, чтобы сформировать итоговый полином.
    @Override
    public void interpolate(List<Point> points) {
        Polynomial polynomial = new Polynomial();
        for (int i = 0; i < points.size(); i++) {
            var tmp = getL(i, points).times(points.get(i).y); // = l_i * y_i
            polynomial = polynomial.plus(tmp);
        }
        this.coeffs = polynomial.getCoeffs();
    }

    // Этот метод создает полином l_i, который представляет собой произведение
    // (x - x_j) / (x_i - x_j) для всех j, не равных i.

    private Polynomial getL(int i, List<Point> table) { // = l_i
        double x_i = table.get(i).x;
        Polynomial polynomial = new Polynomial(1.);
        for (int j = 0; j < table.size(); j++) {
            if (j == i) {
                continue;
            }
            double x_j = table.get(j).x;
            Polynomial tmp = new Polynomial(-x_j / (x_j - x_i), 1. / (x_j - x_i));
            polynomial = polynomial.times(tmp);
        }
        return polynomial;
    }
}
