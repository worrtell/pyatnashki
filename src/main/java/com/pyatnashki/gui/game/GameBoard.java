package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    GameBoard() {
        super();
        MoveListener moveListener = new MoveListener();
        addKeyListener(moveListener);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.add(new JButton("0"));
        buttonPanel.add(new JButton("1"));
        buttonPanel.add(new JButton("2"));
        buttonPanel.add(new JButton("3"));
        buttonPanel.add(new JButton("4"));
        buttonPanel.add(new JButton("5"));
        buttonPanel.add(new JButton("6"));
        buttonPanel.add(new JButton("7"));
        buttonPanel.add(new JButton("8"));
        buttonPanel.setPreferredSize(new Dimension(300, 300));
        setFocusable(true);
        requestFocusInWindow();
        this.add(buttonPanel);

    }

    public static void main(String[] args) throws Exception {
        new GameBoard();
    }
}