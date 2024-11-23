package ru.kpfu.khismatova.lab5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.kpfu.khismatova.lab3.interpolation.Point;
import ru.kpfu.khismatova.lab5.math.Lagrange;
import ru.kpfu.khismatova.lab5.math.Newton;

import java.util.List;

public class InterpolationTest {

    static final List<Point> table1 = List.of(
            new Point(0, -1),
            new Point(1, 2),
            new Point(2, 4)
    );

    @Test
    void lagrangeAndNewtonAreEquals() {
        var lagrange = new Lagrange();
        lagrange.interpolate(table1);

        var newton = new Newton();
        newton.interpolate(table1);

        Assertions.assertEquals(lagrange, newton);
    }

}
