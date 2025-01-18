package ru.kpfu.khismatova.lab8;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class Mandelbrot {

    private static final float SIZE = 2f;

    private final int width;

    private final int height;

    public Mandelbrot(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public BufferedImage drawMandelbrotSet(int power, int maxIter, float brightness) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        IntStream.range(0, width).parallel().forEach(
                x -> {
                    for (int y = 0; y < height; y++) {
                        Complex z = new Complex(0, 0);

                        Complex c = new Complex(
                                (x - width / SIZE) * SIZE * SIZE / width,
                                (y - height / SIZE) * SIZE * SIZE / height
                        );

                        int iter = 0;

                        while (z.magnitudeSquared() < SIZE * SIZE && iter < maxIter) {
                            z = z.power(power).add(c);
                            iter++;
                        }

                        int color = (iter == maxIter)
                                ? 0
                                : Color.HSBtoRGB((float) iter / maxIter, 0.7f, brightness);

                        image.setRGB(x, y, color);
                    }
                }
        );

        return image;
    }
}
