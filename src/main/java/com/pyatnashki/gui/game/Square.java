package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Square extends JButton {
    private StretchIcon icon;
    private String value;

    public Square(String image, String value) {
        super();
        if (image != null) {
            this.icon = new StretchIcon(image);
            this.setIcon(icon);
        }
        this.value = value;
        removeActionListener(this.actionListener);
        Arrays.stream(this.getMouseListeners()).forEach(this::removeMouseListener);
        this.setBackground(new Color(255, 255, 255));
        this.setBorderPainted(true);
        this.setName(value);
    }


}
