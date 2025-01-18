package ru.kpfu.khismatova.lab8;

public record Complex(double real, double imaginary) {
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
