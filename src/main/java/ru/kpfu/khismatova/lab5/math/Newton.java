package ru.kpfu.khismatova.lab5.math;

import ru.kpfu.khismatova.lab3.interpolation.Point;

import java.util.List;

public class Newton extends Polynomial implements Interpolation {

    /**
     * интерполяция с использованием метода Ньютона.
     * <p>
     * Этот метод вычисляет интерполяционный полином Ньютона для заданного списка точек.
     * Он использует разделенные разности для построения полинома и обновляет коэффициенты
     * текущего объекта полинома.
     *
     * @param points Список точек, представляющих данные для интерполяции.
     */
    @Override
    public void interpolate(List<Point> points) {
        double[][] dividedDifferences = computeDividedDifferences(points);
        Polynomial polynomial = new Polynomial();
        for (int i = 0; i < points.size(); i++) { // sum (i = 0 -> n) : f(x_0, ..., x_i) * ((x - x_0) * ... * (x - x_i))
            Polynomial tmp = newtonProd(i, points).times(dividedDifferences[0][i]);
            polynomial = polynomial.plus(tmp);
        }
        this.coeffs = polynomial.getCoeffs();
    }

    /**
     * Вычисляет произведение (x - x_0) * (x - x_1) * ... * (x - x_n-1).
     * <p>
     * Этот метод создает полином, который представляет произведение (x - x_i) для всех i
     * от 0 до n-1, что используется в формуле интерполяции Ньютона.
     *
     * @param n индекс, указывающий до какого элемента из списка точек нужно вычислить произведение.
     * @param points Список точек, представляющих данные для интерполяции.
     * @return Полином, представляющий произведение (x - x_0) * ... * (x - x_n-1).
     */
    private Polynomial newtonProd(int n, List<Point> points) {
        Polynomial polynomial = new Polynomial(1.);
        for (int i = 0; i < n; i++) {
            double x_i = points.get(i).x;
            Polynomial tmp = new Polynomial(-x_i, 1.);
            polynomial = polynomial.times(tmp);
        }
        return polynomial;
    }

    /**
     * Вычисляет разделенные разности для заданного списка точек.
     * <p>
     * Этот метод создает таблицу разделенных разностей, которая используется для
     * построения интерполяционного полинома Ньютона. Разделенные разности
     * позволяют вычислить коэффициенты полинома.
     * <p>
     * P.S.: используется оптимальный метод: за один проход мы считаем
     *       все возможные разделенные разности и записываем их в двумерный массив.
     *
     * @param points Список точек, представляющих данные для интерполяции.
     * @return Двумерный массив, содержащий разделенные разности.
     */
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
