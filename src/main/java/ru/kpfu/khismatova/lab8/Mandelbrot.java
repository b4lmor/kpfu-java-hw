package ru.kpfu.khismatova.lab8;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mandelbrot {

    private static final float SIZE = 2f;

    private final int width;

    private final int height;

    private final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Mandelbrot(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public BufferedImage drawMandelbrotSet(int power, int maxIter, float brightness) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        CountDownLatch latch = new CountDownLatch(width);

        for (int x = 0; x < width; x++) {
            int finalX = x;
            executor.submit(() -> {
                calculateColumn(finalX, image, power, maxIter, brightness);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }

        return image;
    }

    private void calculateColumn(int x, BufferedImage image, int power, int maxIter, float brightness) {
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

    record Complex(double real, double imaginary) {
        public Complex add(Complex other) {
            return new Complex(this.real + other.real, this.imaginary + other.imaginary);
        }

        public Complex multiply(Complex other) {
            return new Complex(this.real * other.real - this.imaginary * other.imaginary,
                    this.real * other.imaginary + this.imaginary * other.real);
        }

        public double magnitudeSquared() {
            return real * real + imaginary * imaginary;
        }

        public Complex power(int exponent) {
            Complex result = new Complex(1, 0);

            for (int i = 0; i < exponent; i++) {
                result = result.multiply(this);
            }

            return result;
        }
    }
}
