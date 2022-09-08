package main;

import entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    long MUSIC_POSITION_PAUSE = 0;
    boolean musicPlaying = true;

    //DEBUG
    boolean checkDrawTime;
    Player player;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) { // PLAY STATE KEYS
            if (code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SHIFT) {
                player.speedBoost = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                MUSIC_POSITION_PAUSE = gp.pauseMusic();
            }
            if (code == KeyEvent.VK_M) { //music toggle with 'M' key
                if (musicPlaying) {
                    gp.stopMusic();
                    musicPlaying = false;
                } else {
                    gp.playMusic(0, false, 0);
                    musicPlaying = true;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }
        else if (gp.gameState == gp.pauseState) { //PAUSE STATE KEYS

            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
                if (musicPlaying) {
                    gp.playMusic(MUSIC_POSITION_PAUSE, true, 0);
                }
            }
        }

        else if (gp.gameState == gp.dialogueState) { //DIALOGUE STATE KEYS
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        //DEBUG
        if (code == KeyEvent.VK_D) {
            if (!checkDrawTime) {
                checkDrawTime = true;
            } else if (checkDrawTime) {
                checkDrawTime = false;
            }
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            player.speedBoost = false;
        }
    }
}
