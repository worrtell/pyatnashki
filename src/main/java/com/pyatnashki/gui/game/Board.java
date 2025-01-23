package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;

public class Board extends JFrame {
    private final JLayeredPane playerBoardOne = new PlayerBoard(new GameBoard(getStartPosition()), 0, 0);
    /* this is board of current player
    so: we need to send server his current position
     */
    private final JLayeredPane playerBoardTwo = new PlayerBoard(new GameBoard(getStartPosition()), 330, 0);
    /* this is board of his opponent
    so: we need to get the start position from server
    */

    /* key codes:
    37, 38, 39, 40
     */
    Board() {
        super();
        add(playerBoardOne, BorderLayout.WEST);
        add(playerBoardTwo, BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 600));
        setLayout(new BorderLayout());
        pack();
        setVisible(true);
    }

    private ArrayList<String> getStartPosition() {
        ArrayList<String> order = new ArrayList<>(asList("0", "1", "2", "3", "4", "5", "6", "7", "8"));
        Collections.shuffle(order);
        return order;
    }

    public static void main(String[] args) {
        new Board();
    }
}
