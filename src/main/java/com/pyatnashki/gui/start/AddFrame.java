package com.pyatnashki.gui.start;

import javax.swing.*;
import java.awt.*;

public class AddFrame extends JFrame {
    private final JButton btnAdd;

    public AddFrame() {
        super("Start");

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        btnAdd = new JButton("Add player");
        btnAdd.addActionListener(e -> addPlayer());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addPlayer() {
        WelcomeFrame welcomeFrame = new WelcomeFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddFrame::new);
    }
}
