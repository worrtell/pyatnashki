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

public class GameBoard extends JPanel implements KeyListener {
    private ArrayList<String> order;
    private JPanel buttonPanel;
    private ArrayList<Integer> goLeft = new ArrayList<>(asList(0, 1, 3, 4, 6, 7));
    private ArrayList<Integer> goRight = new ArrayList<>(asList(1, 2, 4, 5, 7, 8));
    private ArrayList<Integer> goUp = new ArrayList<>(asList(0, 1, 2, 3, 4, 5));
    private ArrayList<Integer> goDown = new ArrayList<>(asList(3, 4, 5, 6, 7, 8));
    private ArrayList<Integer> moveKeys = new ArrayList<>(asList(37, 38, 39, 40));

    private static User user;
    BoardDataSource dataSource;

    GameBoard(ArrayList<String> order, User user) {
        super();
        this.order = order;

        GameBoard.user = user;
        dataSource = new BoardDataSource();
        System.out.println("Added first user info");
        dataSource.onMove(user);

        addKeyListener(this);
        setBoard();
        setFocusable(true);
        requestFocusInWindow();
    }

    private void setBoard() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        for (String square : order) {
            buttonPanel.add(new JButton(square));
        }
        buttonPanel.setPreferredSize(new Dimension(300, 300));
        this.add(buttonPanel);
        this.revalidate();
        this.repaint();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (moveKeys.contains(e.getKeyCode())) {
            if (e.getKeyCode() == 37 && goLeft.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") + 1);
            } else if (e.getKeyCode() == 39 && goRight.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") - 1);
            } else if (e.getKeyCode() == 38 && goUp.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") + 3);
            } else if (e.getKeyCode() == 40 && goDown.contains(order.indexOf("0"))) {
                Collections.swap(order, order.indexOf("0"), order.indexOf("0") - 3);
            }
            this.remove(buttonPanel);

            // i guess here we need to make http post
            System.out.println("Added user info + set keycode " + user.getName());
            user.setKeycode(e.getKeyCode());
            dataSource.onMove(user);
            setBoard();
        }
    }

    public void keyPressed(int keyCode) {
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
            this.remove(buttonPanel);
            System.out.println("Set keycode on unmoved");
            // i guess here we need to make http post
            //user.setKeycode(keyCode);
            //dataSource.onMove(user);
            setBoard();
        }
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
                        keyPressed(dataSource.getPairMove(user));
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