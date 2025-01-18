package ru.kpfu.khismatova.lab8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
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
                state ->
                        new Mandelbrot(2000, 2000)
                                .drawMandelbrotSet(state.power(), state.maxIter(), state.brightness())
                                .getSubimage(state.offsetX(), state.offsetY(), state.width(), state.height())
        );
    }

    private void update() {
        ImageState state = new ImageState(
                this.controlPanel.powerSlider.getValue(),
                this.controlPanel.maxIterationSlider.getValue(),
                this.controlPanel.brightnessSlider.getValue(),
                this.imagePanel.offsetX,
                this.imagePanel.offsetY,
                this.imagePanel.width,
                this.imagePanel.height
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
}
