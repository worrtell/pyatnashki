package com.pyatnashki.gui.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Square {
    private JButton button;
    private Icon icon;
    private String value;

    public Square(String image, String value) throws IOException {
        this.icon = new ImageIcon(image);
        this.value = value;
        this.button = new JButton(icon);
    }

    public JButton getButton() {
        return button;
    }
}
