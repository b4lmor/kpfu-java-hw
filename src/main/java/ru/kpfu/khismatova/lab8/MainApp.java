package ru.kpfu.khismatova.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;
import java.util.UUID;
import java.util.function.Function;

public class MainApp extends JFrame {

    private final ControlPanel controlPanel;

    private final ImagePanel imagePanel;

    private final Stack<ImageState> poseStack = new Stack<>();

    private final Function<ImageState, BufferedImage> imageFunc;

    public MainApp(Function<ImageState, BufferedImage> imageFunc) {

        this.imageFunc = imageFunc;

        setTitle("Mandelbrot Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        this.controlPanel = new ControlPanel();
        add(controlPanel, BorderLayout.WEST);

        this.imagePanel = new ImagePanel();
        add(imagePanel, BorderLayout.CENTER);

        setupKeyBindings();

        update();

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainApp(
                state -> {
                    BufferedImage mandelbrot = new Mandelbrot(2000, 2000)
                            .drawMandelbrotSet(state.power(), state.maxIter(), state.brightness());
                    try {
                        return mandelbrot.getSubimage(state.offsetX(), state.offsetY(), state.width(), state.height());

                    } catch (Exception e) {
                        return mandelbrot;
                    }
                }
        );
    }

    private void update() {

        ImagePanel.ImageOffsetState offsetState = this.imagePanel.getOffsetState();

        ImageState state = new ImageState(
                this.controlPanel.powerSlider.getValue(),
                this.controlPanel.maxIterationSlider.getValue(),
                this.controlPanel.brightnessSlider.getValue(),
                offsetState.x(),
                offsetState.y(),
                offsetState.size(),
                offsetState.size()
        );

        if (!poseStack.isEmpty() && state.equals(poseStack.peek())) {
            return;
        }

        this.poseStack.push(state);

        setImage(imageFunc.apply(this.poseStack.peek()));
    }

    private void undo() {
        if (this.poseStack.isEmpty()) {
            return;
        }

        ImageState state = this.poseStack.pop();

        this.controlPanel.powerSlider.setValue(state.power());
        this.controlPanel.maxIterationSlider.setValue(state.maxIter());
        this.controlPanel.brightnessSlider.setValue(Math.round(state.brightness()));

        if (!this.poseStack.isEmpty()) {
            var cur = this.poseStack.peek();
            if (!imagePanel.poseStack.peek().equals(new ImagePanel.ImageOffsetState(cur.offsetX, cur.offsetY, cur.width))) {
                this.imagePanel.undo();
            }
        }


        setImage(imageFunc.apply(state));
    }

    private void setupKeyBindings() {
        this.controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enterPressed");
        this.controlPanel.getActionMap().put("enterPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enter pressed! Updating ...");

                update();

                System.out.println("Enter pressed! Updating ... Done!");
            }
        });

        this.controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "ctrlZPressed");
        this.controlPanel.getActionMap().put("ctrlZPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ctrl+Z pressed! Undoing ...");

                undo();

                System.out.println("Ctrl+Z pressed! Undoing ... Done!");
            }
        });

        this.controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control S"), "ctrlSPressed");
        this.controlPanel.getActionMap().put("ctrlSPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ctrl+S pressed! Saving ...");

                ImageState.save(UUID.randomUUID() + ".mndlbrt", poseStack.peek());

                System.out.println("Ctrl+S pressed! Saving ... Done!");
            }
        });

        this.controlPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control L"), "ctrlLPressed");
        this.controlPanel.getActionMap().put("ctrlLPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ctrl+L pressed! Loading ...");

                ImageState imageState = ImageState.load(controlPanel.loadField.getText());

                if (imageState != null) {
                    poseStack.push(imageState);
                    setImage(imageFunc.apply(poseStack.peek()));
                }

                System.out.println("Ctrl+L pressed! Loading ... Done!");
            }
        });
    }

    private void setImage(BufferedImage img) {
        this.imagePanel.setImage(img);
        imagePanel.repaint();
    }

    public record ImageState(
            int power,

            int maxIter,

            float brightness,

            int offsetX,

            int offsetY,

            int width,

            int height

    ) implements Serializable {

        public static void save(String path, ImageState record) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
                oos.writeObject(record);
                System.out.println("Объект сохранен в файл: " + path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static ImageState load(String path) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
                ImageState record = (ImageState) ois.readObject();
                System.out.println("Объект загружен из файла: " + path);
                return record;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public static class ImagePanel extends JPanel {

        public BufferedImage image;

        private final Rectangle selection = new Rectangle();

        private float scaleX;

        private float scaleY;

        private final Stack<ImageOffsetState> poseStack = new Stack<>();

        public ImagePanel() {
            setPreferredSize(new Dimension(550, 600));

            MouseAdapter mouseAdapter = getMouseAdapter();

            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);

            poseStack.push(new ImageOffsetState(0, 0, 2000));
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

        public void undo() {
            if (this.poseStack.size() > 1) {
                poseStack.pop();
            }
        }

        public ImageOffsetState getOffsetState() {
            return poseStack.peek();
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

                    int newSize = (int) Math.min(selection.width / scaleX, selection.height / scaleY);

                    ImageOffsetState state = poseStack.peek();

                    int offsetX = state.x;
                    int offsetY = state.y;
                    int size;

                    offsetX += (int) (selection.x / scaleX);
                    offsetY += (int) (selection.y / scaleY);
                    size = newSize;

                    poseStack.push(new ImageOffsetState(offsetX, offsetY, size));

                    selection.setBounds(0, 0, 0, 0);

                    repaint();
                }
            };
        }

        public record ImageOffsetState(

                int x,

                int y,

                int size

        ) {
        }
    }

    public static class ControlPanel extends JPanel {

        public JSlider powerSlider;

        public JSlider brightnessSlider;

        public JSlider maxIterationSlider;

        public JTextField loadField;

        public ControlPanel() {
            this.setLayout(new GridLayout(4, 2));
            this.setPreferredSize(new Dimension(200, 600));

            setupSliders();
        }

        private void setupSliders() {
            this.add(new JLabel("Power"));
            powerSlider = new JSlider(0, 10, 2);
            powerSlider.setMajorTickSpacing(2);
            powerSlider.setPaintLabels(true);
            this.add(powerSlider);

            this.add(new JLabel("Brightness"));
            brightnessSlider = new JSlider(0, 100, 1);
            brightnessSlider.setMajorTickSpacing(50);
            brightnessSlider.setPaintLabels(true);
            this.add(brightnessSlider);

            this.add(new JLabel("Max Iteration"));
            maxIterationSlider = new JSlider(0, 1000, 200);
            maxIterationSlider.setMajorTickSpacing(500);
            maxIterationSlider.setPaintLabels(true);
            this.add(maxIterationSlider);

            this.add(new JLabel("Load file:"));
            loadField = new JTextField(10);
            this.add(loadField);
        }

    }
}
