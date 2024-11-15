package ru.kpfu.khismatova.lab3.interpolation;

import java.util.List;
import java.util.function.Function;

public interface InterpolationFunction {

    Function<Double, Double> interpolate(List<Point> table);

}
