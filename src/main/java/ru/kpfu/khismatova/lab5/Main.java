package ru.kpfu.khismatova.lab5;

import ru.kpfu.khismatova.lab3.interpolation.Point;
import ru.kpfu.khismatova.lab5.math.Lagrange;
import ru.kpfu.khismatova.lab5.math.Newton;

import java.util.List;

public class Main {

    static final List<Point> table = List.of(
            new Point(0, -1),
            new Point(1, 2),
            new Point(2, 4)
    );

    public static void main(String[] args) {
        var lagrange = new Lagrange();
        lagrange.interpolate(table);
        System.out.println(lagrange);

        var newton = new Newton();
        newton.interpolate(table);
        System.out.println(newton);
    }
}