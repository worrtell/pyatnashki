package com.pyatnashki.gui;

import javax.swing.*;
import java.awt.*;

public class WaitFrame extends JFrame {
    public WaitFrame() {
        super("Waiting for the second player");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("Waiting for the second player to join...", JLabel.CENTER);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        ImageIcon loadingIcon = new ImageIcon("fun.gif");
        JLabel lblLoading = new JLabel(loadingIcon, JLabel.CENTER);
        add(lblLoading, BorderLayout.CENTER);

        setVisible(true);
    }
}
