package com.pyatnashki.gui.game;


import com.pyatnashki.data.BoardDataSource;
import com.pyatnashki.data.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;


import static java.util.Arrays.asList;

public class PlayerBoard extends JLayeredPane implements KeyListener {

    private final JLabel movesMadeLabel;
    private final GameBoard gameBoard;
    private final String movesMade = "moves made: %s";
    private int movesCounter;
    private static User user;

    BoardDataSource dataSource;
    private final ArrayList<Integer> goLeft = new ArrayList<>(asList(0, 1, 3, 4, 6, 7));
    private final ArrayList<Integer> goRight = new ArrayList<>(asList(1, 2, 4, 5, 7, 8));
    private final ArrayList<Integer> goUp = new ArrayList<>(asList(0, 1, 2, 3, 4, 5));
    private final ArrayList<Integer> goDown = new ArrayList<>(asList(3, 4, 5, 6, 7, 8));
    private final ArrayList<Integer> moveKeys = new ArrayList<>(asList(37, 38, 39, 40));

    PlayerBoard(GameBoard gameBoard, int x, int y) {
        super();
        this.user = user;
        dataSource = new BoardDataSource();
        dataSource.onMove(user);
        movesCounter = 0;
        this.gameBoard = gameBoard;
        setBounds(x, y, 300, 600);
        this.gameBoard.setBounds(0, 0, 300, 300);
        this.gameBoard.setOpaque(true);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        add(gameBoard, 0, 0);
        movesMadeLabel = new JLabel(movesMade.formatted(0));
        movesMadeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        movesMadeLabel.setBounds(x + 50, y + 350, 200, 100);
        movesMadeLabel.setOpaque(true);
        add(movesMadeLabel, 0, 1);

    }

    public void setMovesMade() {
        movesMadeLabel.setText(movesMade.formatted(movesCounter));
        this.revalidate();
        this.repaint();
    }

    public void tryMakingMove(int keyCode) {
        ArrayList<String> order = gameBoard.getOrder();
        if (moveKeys.contains(keyCode)) {
            if (keyCode == 37 && goLeft.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") + 1);
            } else if (keyCode == 39 && goRight.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") - 1);
            } else if (keyCode == 38 && goUp.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") + 3);
            } else if (keyCode == 40 && goDown.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") - 3);
            }
            movesCounter++;
            gameBoard.remove(gameBoard.getButtonPanel());
            setMovesMade();
            gameBoard.setBoard();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        tryMakingMove(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void reset() {
        Thread thread = new Thread()
        {
            public void run()
            {
                while (true){
                    // and here we get new KeyCode
                    System.out.println("try reset board");
                    int gotKeyPressed = dataSource.getPairMove(user);
                    if (gotKeyPressed != 0) {
                        System.out.println("got move");
                        tryMakingMove(dataSource.getPairMove(user));
                    }
                    else {
                        System.out.println("no move");
                    }
                    try
                    {
                        Thread.sleep(2000); // 1 second
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

}
