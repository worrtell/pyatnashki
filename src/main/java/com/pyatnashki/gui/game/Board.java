package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.util.Arrays.asList;

public class Board extends JFrame {
    private JLayeredPane lpane1 = new PlayerBoard(new GameBoard(new ArrayList<>(asList("0","1","2","3","4","5","6","7","8"))),0,0);
    private JLayeredPane lpane2 = new PlayerBoard(new GameBoard(new ArrayList<>(asList("0","1","2","3","4","5","6","7","8"))),330,0);

    /* key codes:
    37, 38, 39, 40
     */
    Board() {
        super();
        add(lpane1, BorderLayout.CENTER);
        add(lpane2, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 600));
        setLayout(new BorderLayout());
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Board();
    }
}
