package ru.kpfu.khismatova.lection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    Dimension minSz = new Dimension(600, 500);
    JLabel lbl1 = new JLabel();
    JTextField tf1 = new JTextField();
    JButton btn1 = new JButton();

    public MainFrame() {
        setMinimumSize(minSz);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        lbl1.setText("Это метка");
        btn1.setText("Скопировать");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lbl1.setText(tf1.getText());
            }
        });

        var gl1 = new GroupLayout(getContentPane());
        setLayout(gl1);

        gl1.setVerticalGroup(
                gl1.createSequentialGroup()
                        .addGap(8)
                        .addComponent(tf1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
                        .addComponent(btn1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
                        .addComponent(lbl1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
        );
        gl1.setHorizontalGroup(
                gl1.createSequentialGroup()
                        .addGap(8)
                        .addGroup(
                                gl1.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(tf1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                        .addComponent(btn1, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbl1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(8)
        );
    }
}
