package ru.kpfu.khismatova.lab1.task3;

import javax.swing.*;
import java.awt.*;

public class Task3 extends JFrame {

    public Task3() {
        setTitle("task 3");
        setSize(550, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("src/main/resources/bunny.png");
        JLabel label = new JLabel(imageIcon);
        add(label, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Task3();
    }
}
