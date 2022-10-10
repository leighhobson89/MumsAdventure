package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, spacePressed, throwKeyPressed;
    long MUSIC_POSITION_PAUSE = 0;
    boolean musicPlaying = true;

    //DEBUG
    boolean showDebugText;

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
            try {
                titleState(code);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        } else if (gp.gameState == gp.playState) { // PLAY STATE KEYS
            playState(code);
        } else if (gp.gameState == gp.pauseState) { //PAUSE STATE KEYS
            pauseState(code);
        } else if (gp.gameState == gp.dialogueState) { //DIALOGUE STATE KEYS
            dialogueState(code);
        } else if (gp.gameState == gp.characterState) { //CHARACTER STATE KEYS
            characterState(code);
        } else if (gp.gameState == gp.optionsState) { //OPTIONS STATE KEYS
            optionsState(code);
        } else if (gp.gameState == gp.gameOverState) { //GAME OVER STATE KEYS
            gameOverState(code);
        }  else if (gp.gameState == gp.tradeState) { //TRADE STATE KEYS
            tradeState(code);
        }  else if (gp.gameState == gp.mapState) { //MAP STATE KEYS
            mapState(code);
        }
    }

    public void titleState(int code) throws InterruptedException {
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSFX(10);
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSFX(10);
            }
            if (code == KeyEvent.VK_ENTER) { //NEW GAME
                if (gp.ui.commandNum == 0) {
                    gp.ui.titleScreenState = 1;
                    gp.playSFX(11);
                }
                if (gp.ui.commandNum == 1) { //LOAD GAME
                    gp.playSFX(11);
                    //TO DO
                }
                if (gp.ui.commandNum == 2) { //QUIT
                    gp.playSFX(11);
                    Thread.sleep(500);
                    System.exit(0);
                }
            }
        } else if (gp.ui.titleScreenState == 1) {
            if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 3;
                }
                gp.playSFX(10);
            }
            if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 3) {
                    gp.ui.commandNum = 0;
                }
                gp.playSFX(10);
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
                gp.playSFX(11);
            }
        }
    }
    public void playState(int code) {
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
            if (code == KeyEvent.VK_S) { //music toggle with 'S' key
                if (musicPlaying) {
                    gp.stopMusic();
                    musicPlaying = false;
                } else {
                    gp.playMusic(0, false, 0);
                    musicPlaying = true;
                }
            }
            if (code == KeyEvent.VK_C) {
                gp.gameState = gp.characterState;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
            if (code == KeyEvent.VK_T) {
                throwKeyPressed = true;
            }
            if (code == KeyEvent.VK_Q) {
                System.exit(0);
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.optionsState;
            }
            if (code == KeyEvent.VK_M) {
                gp.gameState = gp.mapState;
            }
            if (code == KeyEvent.VK_X) {
                if (!gp.map.miniMapOn) {
                    gp.map.miniMapOn = true;
                } else {
                    gp.map.miniMapOn = false;
                }
            }
            //DEBUG
            if (code == KeyEvent.VK_D) {
                showDebugText = !showDebugText; //toggle draw speed information with 'D' key
            }
            if (code == KeyEvent.VK_R) {
                switch(gp.currentMap) {
                    case 0: gp.tileM.loadMap("/maps/world01.txt", 0); //refresh the map after editing the world map file and saving it during gameplay
                    case 1: gp.tileM.loadMap("/maps/upstairs.txt", 1);
                }
            }
    }
    public void pauseState(int code) {
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
    public void dialogueState(int code) {
            if (code == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;
            }
    }
    public void characterState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            gp.player.selectItem();
        }
        if (code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
        playerInventory(code);
    }
    public void optionsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSFX(10);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSFX(10);
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSFX(10);
                }
                if(gp.ui.commandNum == 2 && gp.sfx.volumeScale > 0) {
                    gp.sfx.volumeScale--;
                    gp.playSFX(10);
                }
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSFX(10);
                }
                if(gp.ui.commandNum == 2 && gp.sfx.volumeScale < 5) {
                    gp.sfx.volumeScale++;
                    gp.playSFX(10);
                }
            }
        }

    }
    public void gameOverState(int code) {
        if (code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSFX(10);
        }
        if (code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSFX(10);
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.retry();
                gp.gameState = gp.playState;
            } else if (gp.ui.commandNum == 1) {
                gp.ui.titleScreenState = 0;
                gp.ui.commandNum = 0;
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }
    public void tradeState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (gp.ui.subState == 0) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
                gp.playSFX(10);
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
                gp.playSFX(10);
            }
        }
        if (gp.ui.subState == 1) {
            npcInventory(code);
            if (code == KeyEvent. VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
        if (gp.ui.subState == 2) {
            playerInventory(code);
            if (code == KeyEvent. VK_ESCAPE) {
                gp.ui.subState = 0;
            }
        }
    }
    public void mapState(int code) {
        if (code == KeyEvent.VK_M) {
            gp.gameState = gp.playState;
        }
    }

    public void playerInventory(int code) {
        if (code == KeyEvent.VK_UP) {
            if (gp.ui.playerSlotRow != 0) {
                gp.playSFX(10);
                gp.ui.playerSlotRow--;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.playerSlotRow != 3) {
                gp.playSFX(10);
                gp.ui.playerSlotRow++;
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.playerSlotCol != 0) {
                gp.playSFX(10);
                gp.ui.playerSlotCol--;
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.playerSlotCol != 4) {
                gp.playSFX(10);
                gp.ui.playerSlotCol++;
            }
        }
    }

    public void npcInventory(int code) {
        if (code == KeyEvent.VK_UP) {
            if (gp.ui.npcSlotRow != 0) {
                gp.playSFX(10);
                gp.ui.npcSlotRow--;
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (gp.ui.npcSlotRow != 3) {
                gp.playSFX(10);
                gp.ui.npcSlotRow++;
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (gp.ui.npcSlotCol != 0) {
                gp.playSFX(10);
                gp.ui.npcSlotCol--;
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if (gp.ui.npcSlotCol != 4) {
                gp.playSFX(10);
                gp.ui.npcSlotCol++;
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
            gp.player.speedBoost = false;
        }
        if (code == KeyEvent.VK_T) {
            throwKeyPressed = false;
        }
    }
}
