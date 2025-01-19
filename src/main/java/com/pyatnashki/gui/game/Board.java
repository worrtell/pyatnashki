package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    private JLayeredPane lpane1 = new PlayerBoard(new GameBoard(),0,0);
    private JLayeredPane lpane2 = new PlayerBoard(new GameBoard(),330,0);


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
