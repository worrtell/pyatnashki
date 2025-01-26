package com.pyatnashki.gui.game;

import com.pyatnashki.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import static java.util.Arrays.asList;

public class Board extends JFrame {
    private final PlayerBoard playerBoardOne;
    /* this is board of current player
    so: we need to send server his current position
     */
    private final PlayerBoard playerBoardTwo;
    /* this is board of his opponent
    so: we need to get the start position from server
    */

    /* key codes:
    37, 38, 39, 40
     */
    public Board(User user) {
        super();
        playerBoardOne = new PlayerBoard(new GameBoard(getStartPosition()), user, 0, 0);
        // here for second user we need UserService.getPairOrder(user)
        playerBoardTwo = new PlayerBoard(new GameBoard(getStartPosition()), user, 330, 0);
        add(playerBoardOne, BorderLayout.WEST);
        add(playerBoardTwo, BorderLayout.EAST);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 600));
        setLayout(new BorderLayout());
        pack();
        setVisible(true);
    }

    private ArrayList<String> getStartPosition() {
        // and here we need to put user order
        ArrayList<String> order = new ArrayList<>(asList("0", "1", "2", "3", "4", "5", "6", "7", "8"));
        Collections.shuffle(order);
        return order;
    }

   public void resetGameBoardTwo() {
       playerBoardTwo.reset();
   }

}
