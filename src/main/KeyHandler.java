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

        if (gp.gameState == gp.titleState) { // TITLE STATE KEYS

            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 2;
                    }
                }
                if (code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) { //NEW GAME
                    if (gp.ui.commandNum == 0) {
                        gp.ui.titleScreenState = 1;
                    }
                    if (gp.ui.commandNum == 1) { //LOAD GAME

                    }
                    if (gp.ui.commandNum == 2) { //QUIT
                        System.exit(0);
                    }
                }
            }
            else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 3) {
                        gp.ui.commandNum = 0;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) { //RED
                        gp.ui.colorOutfit = "red";
                        gp.ui.outfitChosen = gp.ui.colorOutfit;
                        gp.gameState = gp.playState;
                        gp.player.getPlayerImage(gp.ui.colorOutfit);
                    }
                    if (gp.ui.commandNum == 1) { //BROWN
                        gp.ui.colorOutfit = "brown";
                        gp.ui.outfitChosen = gp.ui.colorOutfit;
                        gp.gameState = gp.playState;
                        gp.player.getPlayerImage(gp.ui.colorOutfit);
                    }
                    if (gp.ui.commandNum == 2) { //PURPLE
                        gp.ui.colorOutfit = "purple";
                        gp.ui.outfitChosen = gp.ui.colorOutfit;
                        gp.gameState = gp.playState;
                        gp.player.getPlayerImage(gp.ui.colorOutfit);
                    }
                    if (gp.ui.commandNum == 3) { //BACK
                        gp.ui.titleScreenState = 0;
                        gp.ui.commandNum = 0;
                    }
                    gp.player.getPlayerAttackImage(gp.ui.outfitChosen);
                }
            }
        }

        else if (gp.gameState == gp.playState) { // PLAY STATE KEYS
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
                gp.player.speedBoost = true;
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
            if (code == KeyEvent.VK_Q) {
                System.exit(0);
            }
        }
        else if (gp.gameState == gp.pauseState) { //PAUSE STATE KEYS

            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
                if (musicPlaying) {
                    gp.playMusic(MUSIC_POSITION_PAUSE, true, 0);
                }
            }
            if (code == KeyEvent.VK_Q) {
                System.exit(0);
            }
        }

        else if (gp.gameState == gp.dialogueState) { //DIALOGUE STATE KEYS
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
        }

        //DEBUG
        if (code == KeyEvent.VK_D) {
            checkDrawTime = !checkDrawTime; //toggle draw speed information with 'D' key
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
            gp.player.speedBoost = false;
        }
    }
}
