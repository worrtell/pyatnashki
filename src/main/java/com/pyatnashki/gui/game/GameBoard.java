package com.pyatnashki.gui.game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class GameBoard extends JPanel  {
    private final ArrayList<String> order;
    private JPanel buttonPanel;

    Map<String, String> board = Map.of(
            "0", "D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\zero.png",
            "1", "D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\one.png",
            "2","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\two.png",
            "3","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\three.png",
            "4","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\four.png",
            "5","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\five.png",
            "6","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\six.png",
            "7","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\seven.png",
            "8","D:\\IdeaProjects\\pyatnashki\\src\\main\\resources\\images\\eight.png"
    );

    GameBoard(ArrayList<String> order) {
        super();
        this.order = order;

        setBoard();

    }

    public void setBoard() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        for (String square : order) {
            buttonPanel.add(new Square(board.get(square), square));
        }
        buttonPanel.setPreferredSize(new Dimension(300, 300));
        this.add(buttonPanel);
        this.revalidate();
        this.repaint();
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public ArrayList<String> getOrder() {
        return order;
    }
}