package com.pyatnashki.gui.game;


import com.pyatnashki.model.User;
import com.pyatnashki.service.UserRequestService;

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
    private User user;
    private Stopwatch stopwatch;

    private UserRequestService requestService;
    private final ArrayList<Integer> goLeft = new ArrayList<>(asList(0, 1, 3, 4, 6, 7));
    private final ArrayList<Integer> goRight = new ArrayList<>(asList(1, 2, 4, 5, 7, 8));
    private final ArrayList<Integer> goUp = new ArrayList<>(asList(0, 1, 2, 3, 4, 5));
    private final ArrayList<Integer> goDown = new ArrayList<>(asList(3, 4, 5, 6, 7, 8));
    private final ArrayList<Integer> moveKeys = new ArrayList<>(asList(37, 38, 39, 40));

    PlayerBoard(GameBoard gameBoard, User user, int x, int y) {
        super();
        setBounds(x, y, 300, 600);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        this.user = user;
        this.requestService = new UserRequestService();
        this.requestService.onMove(user);
        this.movesCounter = 0;

        this.gameBoard = gameBoard;
        this.gameBoard.setBounds(0, 0, 300, 300);
        this.gameBoard.setOpaque(true);
        add(gameBoard, 0, 0);

        this.movesMadeLabel = new JLabel(movesMade.formatted(0));
        this.movesMadeLabel.setFont(new Font("Courier New", Font.PLAIN, 20));
        this.movesMadeLabel.setBounds(30, 330, 200, 100);
        this.movesMadeLabel.setOpaque(true);
        this.movesMadeLabel.setVisible(true);
        add(movesMadeLabel);

        this.stopwatch = new Stopwatch();
        this.stopwatch.setBounds(30, 430, 300, 100);
        this.stopwatch.setOpaque(true);
        this.stopwatch.setVisible(true);
        add(stopwatch);

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
        user.setKeycode(e.getKeyCode());
        requestService.onMove(user);
        tryMakingMove(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void reset() {
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    // and here we get new KeyCode
                    System.out.println("try reset board");
                    int gotKeyPressed = requestService.getPairMove(user);
                    if (gotKeyPressed != 0) {
                        System.out.println("got move");
                        tryMakingMove(gotKeyPressed);
                    } else {
                        System.out.println("no move");
                    }
                    try {
                        Thread.sleep(2000); // 1 second
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

}
