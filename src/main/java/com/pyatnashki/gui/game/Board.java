package com.pyatnashki.gui.game;

import com.pyatnashki.data.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static java.util.Arrays.asList;

public class Board extends JFrame {
    private JLayeredPane lpane1;
    private JLayeredPane lpane2;
    GameBoard gameBoard1;
    GameBoard gameBoard2;

    /* key codes:
    37, 38, 39, 40
     */

    // problem - two frames one keyboard
    public Board(LinkedList<User> users) {
        super();

        gameBoard1 = new GameBoard(new ArrayList<>(asList("0","1","2","3","4","5","6","7","8")), users.get(0));
        gameBoard2 = new GameBoard(new ArrayList<>(asList("0","1","2","3","4","5","6","7","8")), users.get(1));

        lpane1 = new PlayerBoard(gameBoard1,0,0);
        lpane2 = new PlayerBoard(gameBoard2,330,0);

        add(lpane1, BorderLayout.CENTER);
        add(lpane2, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 600));
        setLayout(new BorderLayout());
        pack();
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new Board();
//    }

    public void resetGameBoard2() {
        gameBoard2.reset();
    }
}
