package ru.kpfu.khismatova.lab5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.kpfu.khismatova.lab3.interpolation.Point;
import ru.kpfu.khismatova.lab5.math.Interpolation;
import ru.kpfu.khismatova.lab5.math.Lagrange;
import ru.kpfu.khismatova.lab5.math.Newton;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterpolationTest {

    static Stream<List<Point>> pointProvider() {
        return Stream.of(
                List.of(
                        new Point(0, -1),
                        new Point(1, 2),
                        new Point(2, 4),
                        new Point(3, 7),
                        new Point(4, 10)
                ),
                List.of(
                        new Point(0, 0),
                        new Point(1, 1),
                        new Point(2, 4),
                        new Point(3, 9),
                        new Point(4, 16)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void lagrangeAndNewtonAreEquals(List<Point> points) {
        Interpolation lagrange = new Lagrange();
        lagrange.interpolate(points);

        Interpolation newton = new Newton();
        newton.interpolate(points);

        assertEquals(lagrange, newton);
    }

    @ParameterizedTest
    @MethodSource("pointProvider")
    void timeTest(List<Point> points) {
        Interpolation lagrange = new Lagrange();

        long startTimeLagrange = System.nanoTime();
        lagrange.interpolate(points);
        long endTimeLagrange = System.nanoTime();

        long durationLagrange = endTimeLagrange - startTimeLagrange;
        System.out.println("Lagrange: " + durationLagrange + " ns");

        Interpolation newton = new Newton();

        long startTimeNewton = System.nanoTime();
        newton.interpolate(points);
        long endTimeNewton = System.nanoTime();

        long durationNewton = endTimeNewton - startTimeNewton;
        System.out.println("Newton: " + durationNewton + " ns");
    }
}