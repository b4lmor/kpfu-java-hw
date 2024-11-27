package ru.kpfu.khismatova.lab5.math;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private static final double EPSILON = 1e-4;

    protected List<Double> coeffs;

    public List<Double> getCoeffs() {
        return new ArrayList<>(coeffs);
    }

    public Polynomial() {
        this.coeffs = new ArrayList<>();
        coeffs.add(0.0);
    }

    public Polynomial(List<Double> coeffs) {
        this.coeffs = new ArrayList<>();
        this.coeffs.addAll(coeffs);
        correctCoeffs();
    }

    public Polynomial(Double... coeffs) {
        this.coeffs = new ArrayList<>();
        this.coeffs.addAll(List.of(coeffs));
        correctCoeffs();
    }

    public int getPower() {
        return coeffs.size() - 1;
    }

    private void correctCoeffs() {
        while (coeffs.size() > 1) {
            if (coeffs.get(getPower()) == 0) {
                coeffs.remove(getPower());
            } else {
                break;
            }
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = getPower(); i >= 0; i--) {
            var k = coeffs.get(i);
            if (k != 0.0) {
                if (k < 0)
                    sb.append("-");
                else if (i != getPower())
                    sb.append("+");
                if (Math.abs(k) != 1.0 || i == 0)
                    sb.append(Math.abs(k));
                if (i > 0) sb.append('x');
                if (i > 1) {
                    sb.append('^');
                    sb.append(i);
                }
            }
        }
        if (sb.isEmpty()) sb.append('0');
        return sb.toString();
    }

    public Polynomial times(double k) {
        var l = new ArrayList<Double>();
        for (var c : coeffs) {
            l.add(c * k);
        }
        return new Polynomial(l);
    }

    public double calc(double x) {
        double p = 1;
        double s = coeffs.getFirst();
        for (int i = 1; i < coeffs.size(); i++) {
            p *= x;
            s += coeffs.get(i) * p;
        }
        return s;
    }

    public Polynomial times(Polynomial other) {
        // (ax^2 + bx + c)(dx + e) = adx^3+bdx^2+cdx+aex^2+bex+ce=adx^3+(bd+ae)x^2+(cd+be)x+ce
        var c = new ArrayList<Double>(coeffs.size() + other.coeffs.size());
        for (int i = 0; i < coeffs.size() + other.coeffs.size(); i++) {
            c.add(0.);
        }
        for (int i = 0; i < coeffs.size(); i++) {
            for (int j = 0; j < other.coeffs.size(); j++) {
                c.set(i + j, c.get(i + j) + coeffs.get(i) * other.coeffs.get(j));
            }
        }
        return new Polynomial(c);
    }

    public Polynomial minus(Polynomial other) {
        return plus(other.times(-1.));
    }

    public Polynomial div(double k) {
        return times(1. / k);
    }

    public Polynomial plus(Polynomial other) {
        var c = new ArrayList<>(
                getPower() >= other.getPower() ? coeffs : other.coeffs
        );
        if (getPower() >= other.getPower()) {
            for (int i = 0; i < other.coeffs.size(); i++) {
                c.set(i, other.coeffs.get(i) + c.get(i)); // c[i] = other.coeffs[i] + c[i]
            }
        } else {
            for (int i = 0; i < coeffs.size(); i++) {
                c.set(i, coeffs.get(i) + c.get(i));
            }
        }
        return new Polynomial(c);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Polynomial polynomial) {
            int maxSize = Math.max(this.coeffs.size(), polynomial.coeffs.size());
            for (int i = 0; i < maxSize; i++) {
                double self = i >= this.coeffs.size() ? 0 : this.coeffs.get(i);
                double other = i >= polynomial.coeffs.size() ? 0 : polynomial.coeffs.get(i);
                if (Math.abs(self - other) >= EPSILON) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return coeffs.hashCode();
    }
}
