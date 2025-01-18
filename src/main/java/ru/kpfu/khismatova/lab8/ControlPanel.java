package ru.kpfu.khismatova.lab8;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

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
