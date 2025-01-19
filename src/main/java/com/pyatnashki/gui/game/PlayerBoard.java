package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PlayerBoard extends JLayeredPane {
    PlayerBoard(JPanel gameBoard, int x, int y) {
        setBounds(x, y, 300, 600);
        gameBoard.setBounds(0, 0, 300, 300);
        gameBoard.setOpaque(true);
        add(gameBoard,0, 0);
    }

}
