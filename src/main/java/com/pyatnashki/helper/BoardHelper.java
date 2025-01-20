package com.pyatnashki.helper;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

//
public class BoardHelper {
    private JPanel gamePanel;

    BoardHelper(JPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public ArrayList<Integer> getSchema() {
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.stream(gamePanel.getComponents()).forEach(
                a -> {System.out.println(a.getName());
                list.add(Integer.parseInt(a.getName()));}
        );
        return list;
    }
}