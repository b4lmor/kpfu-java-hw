package ru.kpfu.khismatova.lab5.math;

import ru.kpfu.khismatova.lab3.interpolation.Point;

import java.util.List;

public class Newton extends Polynomial implements Interpolation {

    @Override
    public void interpolate(List<Point> points) {
        double[][] dividedDifferences = computeDividedDifferences(points);
        Polynomial polynomial = new Polynomial();
        for (int i = 0; i < points.size(); i++) { // sum (i = 0 -> n) : f(x_0, ..., x_i) * (x - x_0) *... * (x - x_i)
            Polynomial tmp = newtonProd(i, points).times(dividedDifferences[0][i]);
            polynomial = polynomial.plus(tmp);
        }
        this.coeffs = polynomial.getCoeffs();
    }

    private Polynomial newtonProd(int n, List<Point> points) { // = (x - x_0) * (x - x_1) * ... * (x - x_n)
        Polynomial polynomial = new Polynomial(1.);
        for (int i = 0; i < n; i++) {
            double x_i = points.get(i).x;
            Polynomial tmp = new Polynomial(-x_i, 1.);
            polynomial = polynomial.times(tmp);
        }
        return polynomial;
    }

    private double[][] computeDividedDifferences(List<Point> points) {
        int n = points.size();
        double[][] dividedDifferences = new double[n][n];
        for (int i = 0; i < n; i++) {
            dividedDifferences[i][0] = points.get(i).y;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < n - j; i++) {
                dividedDifferences[i][j] = (dividedDifferences[i + 1][j - 1] - dividedDifferences[i][j - 1]) /
                        (points.get(i + j).x - points.get(i).x);
            }
        }
        return dividedDifferences;
    }

}
