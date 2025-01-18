package ru.kpfu.khismatova.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {

    public BufferedImage image;

    private final Rectangle selection = new Rectangle();

    private float scaleX;

    private float scaleY;

    public int offsetX = 0;

    public int offsetY = 0;

    public int width = 2000;

    public int height = 2000;

    public ImagePanel() {
        setPreferredSize(new Dimension(550, 600));

        MouseAdapter mouseAdapter = getMouseAdapter();

        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image == null) {
            return;
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(255, 0, 0, 128));
        g2d.fill(selection);

        g2d.setColor(Color.RED);
        g2d.drawRect(selection.x, selection.y, selection.width, selection.height);
    }

    public void setImage(BufferedImage img) {
        this.image = img;
        this.scaleX = 600f / img.getWidth();
        this.scaleY = 600f / img.getHeight();
    }

    private MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            private Point startPoint;

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                selection.setBounds(startPoint.x, startPoint.y, 0, 0);

                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = Math.min(startPoint.x, e.getX());
                int y = Math.min(startPoint.y, e.getY());

                int width = Math.abs(startPoint.x - e.getX());
                int height = Math.abs(startPoint.y - e.getY());

                selection.setBounds(x, y, width, height);

                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Selected area: " + selection);

                int size = (int) Math.min(selection.width / scaleX, selection.height / scaleY);

                offsetX += (int) (selection.x / scaleX);
                offsetY += (int) (selection.y / scaleY);
                width = height = size;

                selection.setBounds(0, 0, 0, 0);

                repaint();
            }
        };
    }
}
