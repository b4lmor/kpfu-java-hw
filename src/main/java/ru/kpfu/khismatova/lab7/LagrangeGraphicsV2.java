package ru.kpfu.khismatova.lab7;

import ru.kpfu.khismatova.lab3.interpolation.Lagrange;
import ru.kpfu.khismatova.lab3.interpolation.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LagrangeGraphicsV2 extends JPanel {

    private static final int SCALE = 50;
    private static final int AXIS_PADDING = 10;
    private final List<Point> points = new ArrayList<>();
    private Function<Double, Double> lagrange;

    public LagrangeGraphicsV2() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                double x = (e.getX() - getWidth() / 2.0) / SCALE;
                double y = -(e.getY() - getHeight() / 2.0) / SCALE;

                points.add(new Point(x, y));
                lagrange = new Lagrange().interpolate(points);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawAxes(g);
        drawPoints(g);
        drawLagrangePolynomial(g);
    }

    private void drawAxes(Graphics g) {
        g.setColor(Color.GRAY);
        int width = getWidth();
        int height = getHeight();

        // Рисуем оси
        g.drawLine(width / 2, 0, width / 2, height); // Y-ось
        g.drawLine(0, height / 2, width, height / 2); // X-ось

        // Отметки на осях
        for (int i = -width / (2 * SCALE); i <= width / (2 * SCALE); i++) {
            int x = i * SCALE + width / 2;
            g.drawLine(x, height / 2 - AXIS_PADDING, x, height / 2 + AXIS_PADDING);
            g.drawString(String.valueOf(i), x - 10, height / 2 + 25);
        }

        for (int i = -height / (2 * SCALE); i <= height / (2 * SCALE); i++) {
            int y = -i * SCALE + height / 2;
            g.drawLine(width / 2 - AXIS_PADDING, y, width / 2 + AXIS_PADDING, y);
            if (i != 0) {
                g.drawString(String.valueOf(i), width / 2 + 10, y + 5);
            }
        }
    }

    private void drawPoints(Graphics g) {
        g.setColor(Color.RED);
        for (Point point : points) {
            int x = (int) (point.x * SCALE + getWidth() / 2);
            int y = (int) (-point.y * SCALE + getHeight() / 2);
            g.fillOval(x - 4, y - 4, 8, 8);
        }
    }

    private void drawLagrangePolynomial(Graphics g) {
        if (points.size() < 2) {
            return;
        }

        g.setColor(Color.BLUE);

        for (int i = 0; i < getWidth(); i++) {

            double x1 = (i - getWidth() / 2.0) / SCALE;
            double y1 = lagrange.apply(x1);

            double x2 = ((i + 1) - getWidth() / 2.0) / SCALE;
            double y2 = lagrange.apply(x2);

            int screenX1 = i;
            int screenY1 = (int) (-y1 * SCALE + getHeight() / 2);

            int screenX2 = i + 1;
            int screenY2 = (int) (-y2 * SCALE + getHeight() / 2);

            g.drawLine(screenX1, screenY1, screenX2, screenY2);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Lagrange Interpolation");

        LagrangeGraphicsV2 mainPanel = new LagrangeGraphicsV2();

        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
