package com.pyatnashki.gui.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MoveListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key typed " + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed " + e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased " + e);
    }
}