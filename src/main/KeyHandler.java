package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            System.out.println("Up!");
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            System.out.println("Down!");
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            System.out.println("Left!");
            leftPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            System.out.println("Right!");
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            System.out.println("Up!");
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            System.out.println("Down!");
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            System.out.println("Left!");
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            System.out.println("Right!");
            rightPressed = false;
        }
    }
}
