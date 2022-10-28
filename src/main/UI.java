package main;

import entity.Entity;
import object.OBJ_Coin;
import object.OBJ_LightningBoltStress;
import object.OBJ_SqueakyToy_UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    public Font breathFire, maruMonica, breathFire_40, breathFire_80, maruMonica_40;
    BufferedImage bolt_full, bolt_half, bolt_blank, squeakyToyFull, squeakyToyEmpty, coin;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue;
    public int commandNum = 0;
    public int titleScreenState = 0; //0: FIRST TITLE SCREEN 1: SECOND SCREEN
    public String colorOutfit = "brown";
    public String outfitChosen = "";
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    public int subState = 0;
    int counter = 0;
    public Entity merchant;

    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;

        InputStream iS = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
        assert iS != null;
        maruMonica = Font.createFont(Font.TRUETYPE_FONT,iS);
        maruMonica_40 = maruMonica.deriveFont(40F);

        //CREATE HUD OBJECT
        Entity bolt = new OBJ_LightningBoltStress(gp);
        bolt_full = bolt.image;
        bolt_half = bolt.image2;
        bolt_blank = bolt.image3;
        Entity squeakyToy = new OBJ_SqueakyToy_UI(gp);
        squeakyToyFull = squeakyToy.image;
        squeakyToyEmpty = squeakyToy.image2;
        Entity goldCoin = new OBJ_Coin(gp);
        coin = goldCoin.down1;
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica_40);
        g2.setColor(Color.WHITE);

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        //PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawMapAreaName(gp.currentMap);
            drawMessage();
        }
        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawMapAreaName(gp.currentMap);
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawMapAreaName(gp.currentMap);
            drawDialogueScreen(0);
        }
        //CHARACTER STATE
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory(gp.player, true);
        }
        //OPTIONS STATE
        if (gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        //GAME OVER STATE
        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        //TRANSITION STATE
        if (gp.gameState == gp.transitionState) {
            drawTransition();
        }
        //TRADE STATE
        if (gp.gameState == gp.tradeState) {
            drawTradeScreen();
        }
        //SLEEP STATE
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
        }
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0,0,0, 150)); // half black half transparent
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        //Shadow
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40, y);
        }
        //Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }

    public void drawMapAreaName(int currentMap) {

        String text = "";
        int x = gp.tileSize*15;
        int y = gp.tileSize/2;

        drawSubWindow(x + 15, y - 17, gp.tileSize*4 + 20, gp.tileSize + 25);

        switch (currentMap) {
            case 0:
                text = "Downstairs";
                g2.drawString(text, x + 40, y + 33);
                break;
            case 1:
                text = "Upstairs";
                g2.drawString(text, x + 65, y + 33);
                break;
        }

    }

    public void drawPlayerLife() {

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

//        g2.setColor(Color.black);
//        g2.fillRoundRect(x - 5, y - 10, gp.tileSize*4, gp.tileSize*2, 10, 10);
        drawSubWindow(x - 15, y - 17, gp.tileSize*4 + 20, gp.tileSize + 25); //height when mana displayed should be ((gp.tilesize*2) + 15)

        //DRAW MAX LIFE
        while (i < gp.player.maxStress /2) {
            g2.drawImage(bolt_blank, x, y, null);
            i++;
            x += gp.tileSize*0.8;
        }

        x = gp.tileSize/2;
        i = 0;

        //DRAW CURRENT LIFE
        while(i < gp.player.stressLevel) {
            g2.drawImage(bolt_half, x, y, null);
            i++;
            if (i < gp.player.stressLevel) {
                g2.drawImage(bolt_full, x, y, null);
            }
            i++;
            x += gp.tileSize*0.8;
        }

//        //DRAW MAX MANA
//        x = (gp.tileSize/2) - 15;
//        y = gp.tileSize + 20;
//        i = 0;
//        while (i < gp.player.maxMana) {
//            g2.drawImage(squeakyToyEmpty, x, y, null);
//            i++;
//            x += 35;
//        }
//
//        //DRAW CURRENT MANA
//        x = (gp.tileSize/2) - 15;
//        y = gp.tileSize + 20;
//        i = 0;
//        while (i < gp.player.mana) {
//            g2.drawImage(squeakyToyFull, x, y, null);
//            i++;
//            x += 35;
//        }
    }

    public void drawMessage() {
        int messageX = gp.tileSize/2 - 15;
        int messageY = gp.tileSize * 3;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));

        for (int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {
                g2.setColor(Color.BLACK);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);
                g2.setColor(Color.WHITE);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) +1; // messageCounter++ but for ArrayList you have to type like this
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color (218, 217, 132));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "Mums Adventure";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            //SHADOW
            g2.setColor(new Color (168, 168, 103));
            g2.drawString(text, x+5, y+5);
            //MAIN COLOR
            g2.setColor(Color.BLACK);
            g2.drawString(text, x, y);

            //MUM IMAGE
            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y += gp.tileSize*2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            //MENU
            g2.setFont((g2.getFont().deriveFont(Font.BOLD, 48F)));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize*3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
        else if (titleScreenState == 1) {
            g2.setColor(new Color (218, 217, 132));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 42F));

            String text = "SELECT YOUR OUTFIT!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "Red";
            x = getXforCenteredText(text) - gp.tileSize*4;
            y = gp.tileSize*9;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
                x = gp.screenWidth/2 - (gp.tileSize*2)/2;
                y = gp.tileSize*5;
                g2.drawImage(gp.player.down1_red, x, y, gp.tileSize*2, gp.tileSize*2, null);
                colorOutfit = "red";
            }

            text = "Brown";
            x = getXforCenteredText(text);
            y = gp.tileSize*9;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
                x = gp.screenWidth/2 - (gp.tileSize*2)/2;
                y = gp.tileSize*5;
                g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
                colorOutfit = "brown";
            }

            text = "Purple";
            x = getXforCenteredText(text) + gp.tileSize*4;
            y = gp.tileSize*9;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
                x = gp.screenWidth/2 - (gp.tileSize*2)/2;
                y = gp.tileSize*5;
                g2.drawImage(gp.player.down1_purple, x, y, gp.tileSize*2, gp.tileSize*2, null);
                colorOutfit = "purple";
            }

            text = "Back";
            x = getXforCenteredText(text);
            y = gp.tileSize*11;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
                colorOutfit = "brown";
            }

            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y = gp.tileSize*5;
            switch (colorOutfit) {
                case "red" -> g2.drawImage(gp.player.down1_red, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
                case "brown" -> g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
                case "purple" -> g2.drawImage(gp.player.down1_purple, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            }
        }


    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(int layout) {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        switch (layout) {
            case 0:
                //WINDOW
                x = gp.tileSize*2;
                y = gp.tileSize*4;
                width = gp.screenWidth - (gp.tileSize*4);
                height = gp.tileSize*4;
                break;
            case 1:
                x = gp.tileSize*3;
                y = gp.tileSize/2;
                width = gp.screenWidth - (gp.tileSize*6);
                height = gp.tileSize*4;
                break;
        }

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }
    public void drawCharacterScreen() {

        //CREATE A FRAME
        final int frameX = gp.tileSize*2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Level", textX, textY); textY += lineHeight;
        g2.drawString("Stress", textX, textY); textY += lineHeight;
        g2.drawString("Toys", textX, textY); textY += lineHeight;
        g2.drawString("Strength", textX, textY); textY += lineHeight;
        g2.drawString("Dexterity", textX, textY); textY += lineHeight;
        g2.drawString("Attack", textX, textY); textY += lineHeight;
        g2.drawString("Defence", textX, textY); textY += lineHeight;
        g2.drawString("Exp", textX, textY); textY += lineHeight;
        g2.drawString("Next Level", textX, textY); textY += lineHeight;
        g2.drawString("Coin", textX, textY); textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY); textY += lineHeight + 15;
        g2.drawString("Armour", textX, textY);

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        // Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gp.player.stressLevel + "/" + gp.player.maxStress;
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = gp.player.mana + "/" + gp.player.maxMana;
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        if (gp.player.currentWeapon != null) {
            g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-24, null);
        }
        textY += gp.tileSize;
        if (gp.player.currentArmour != null) {
            g2.drawImage(gp.player.currentArmour.down1, tailX - gp.tileSize, textY-22, null);
        }
    }

    public void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        //FRAME
        if (entity == gp.player) {
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize* 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize* 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize + 3;

        //DRAW PLAYER'S ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {

            //EQUIP CURSOR
            if (entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentArmour || (entity.inventory.get(i) == entity.currentLight && entity.inventory.get(i).name != "Anti Brightness Pills")) {
                g2.setColor(new Color(240, 190, 90));
                g2. fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);

            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            //DISPLAY AMOUNT
            if(entity == gp.player && entity.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXForAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                // DRAW SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX, amountY);
                //NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX - 3, amountY - 3);
            }

            slotX += slotSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSOR
        if (cursor) {
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //DRAW CURSOR
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            // DESCRIPTION FRAME
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize * 3;

            //DRAW DESCRIPTION TEXT
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if (itemIndex < entity.inventory.size()) {
                drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
                for (String line: entity.inventory.get(itemIndex).description.split("\n")) {
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }

    }

    public void drawOptionsScreen() {
        g2.setColor(Color.WHITE);
        g2. setFont(g2.getFont().deriveFont(32F));

        // SUB WINDOW
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch(subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY);break;
            case 2: options_control(frameX, frameY); break;
            case 3: options_endGameConfirmation(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        //TITLE
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULLSCREEN
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed) {
                gp.playSFX(11);
                if(!gp.fullScreenOn) {
                    gp.fullScreenOn = true;
                } else {
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        //SFX
        textY += gp.tileSize;
        g2.drawString("Sound FX", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX - 25, textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Controls", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 2;
                commandNum = 0;
            }
        }

        //END GAME
        textY += gp.tileSize;
        g2.drawString("End Game", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 3;
                commandNum = 0;
            }
        }

        //BACK
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //FULL SCREEN CHECKBOX
        textX = frameX + (int) (gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 28;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn) {
            g2.fillRect(textX + 4, textY + 4, 17, 16);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX,textY, 120, 24); // 120/5 = 24
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        //SFX VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX,textY, 120, 24); // 120/5 = 24
        volumeWidth = 24 * gp.sfx.volumeScale;
        g2.fillRect(textX, textY, volumeWidth, 24);

        gp.config.saveConfig(); //saves options for loading back next time game starts
    }

    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take\neffect after restarting\nthe game.";

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        // BACK
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 0;
            }
        }
    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        //TITLE
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += gp.tileSize;
        g2.drawString("Confirm / Attack", textX, textY); textY += gp.tileSize;
        g2.drawString("Throw", textX, textY); textY += gp.tileSize;
        g2.drawString("Character Screen", textX, textY); textY += gp.tileSize;
        g2.drawString("Pause", textX, textY); textY += gp.tileSize;
        g2.drawString("Music On / Off", textX, textY); textY += gp.tileSize;
        g2.drawString("Options", textX, textY); textY += gp.tileSize;

        textX = (int) (frameX + gp.tileSize*5.5);
        textY = frameY + gp.tileSize*2 + 3;
        g2.drawString("ARROWS", textX, textY); textY += gp.tileSize;
        g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
        g2.drawString("T", textX, textY); textY += gp.tileSize;
        g2.drawString("C", textX, textY); textY += gp.tileSize;
        g2.drawString("P", textX, textY); textY += gp.tileSize;
        g2.drawString("S", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

        // BACK
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 0;
                commandNum = 3;
            }
        }
    }

    public void options_endGameConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Quit the game and\nreturn to the title screen?";

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //YES
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 0;
                gp.ui.titleScreenState = 0;
                gp.ui.commandNum = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
        //NO
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 0;
                commandNum = 4;
            }
        }
    }

    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }

    public void drawTradeScreen() {
        switch (subState) {
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }

    public void trade_select() { //dialogue options
        drawDialogueScreen(1);

        //DRAW WINDOW
        int x = gp.tileSize * 15;
        int y = gp.tileSize * 4;
        int width = gp.tileSize * 3;
        int height = (int)(gp.tileSize * 3.5);

        drawSubWindow(x, y, width, height);

        //DRAW TEXTS
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 1;
            }
        }
        y += gp.tileSize;
        g2.drawString("Sell", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                subState = 2;
            }
        }
        y += gp.tileSize;
        g2.drawString("Leave", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - 24, y);
            if (gp.keyH.enterPressed) {
                gp.playSFX(11);
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Ok den, I'll wait 'ere while\nyou tink about it innit!";
            }
        }
        y += gp.tileSize;

    }

    public void trade_buy() {
        //DRAW PLAYER INVENTORY
        drawInventory(gp.player, false);
        // DRAW NPC INVENTORY
        drawInventory(merchant, true);

        //DRAW HINT WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[Esc] Back", x + 24, y + 60);

        //DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coin: " + gp.player.coin, x + 24, y + 60);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if (itemIndex < merchant.inventory.size()) {

            x = (int) (gp.tileSize*5.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = merchant.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize*8 - 20);
            g2.drawString(text, x, y + 34);

            //BUY AN ITEM
            if (gp.keyH.enterPressed) {
                if (merchant.inventory.get(itemIndex).price > gp.player.coin) {
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Dat's not enough coin, you have to pay " + price + " innit!";
                } else {
                    if (gp.player.canObtainItem(merchant.inventory.get(itemIndex))) {
                        gp.player.coin -= merchant.inventory.get(itemIndex).price;
                        gp.playSFX(11);
                    } else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "My pockets are all full, I can't carry more!";
                    }
                }
            }
        }
    }

    public void trade_sell() {
        //DRAW PLAYERS INVENTORY
        drawInventory(gp.player, true);

        int x;
        int y;
        int width;
        int height;

        //DRAW HINT WINDOW
        x = gp.tileSize*2;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("[Esc] Back", x + 24, y + 60);

        //DRAW PLAYER COIN WINDOW
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x, y, width, height);
        g2.drawString("Your Coin: " + gp.player.coin, x + 24, y + 60);

        //DRAW PRICE WINDOW
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if (itemIndex < gp.player.inventory.size()) {

            x = (int) (gp.tileSize*15.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x, y, width, height);
            g2.drawImage(coin, x + 10, y + 8, 32, 32, null);

            int price = (int) (gp.player.inventory.get(itemIndex).price*0.7); //selling price modifier
            String text = "" + price;
            x = getXForAlignToRightText(text, gp.tileSize*18 - 20);
            g2.drawString(text, x, y + 34);

            //SELL AN ITEM
            if (gp.keyH.enterPressed) {
                if (gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
                        gp.player.inventory.get(itemIndex) == gp.player.currentArmour || gp.player.inventory.get(itemIndex) == gp.player.currentLight) {
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need to unequip your item before you can sell it!";
                } else {
                    if (gp.player.inventory.get(itemIndex).isSaleable) {
                        if (gp.player.inventory.get(itemIndex).amount > 1) {
                            gp.player.inventory.get(itemIndex).amount--;
                        } else {
                            gp.player.inventory.remove(itemIndex);
                        }
                        gp.player.coin += price;
                        gp.playSFX(22);
                    } else {
                        commandNum = 0;
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You cannot sell this item";
                    }
                }
            }
        }
    }

    public void drawSleepScreen() {
        counter++;

        if (counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.01f;
            if (gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.01f;
            if (gp.eManager.lighting.filterAlpha <= 0f) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.eManager.lighting.dayCounter = 0;
                gp.gameState = gp.dialogueState;
                gp.ui.currentDialogue = "Nice to have a rest, I feel less stressed\nstraight away!\n(Game Saved!)";
            }
        }
    }

    public int getItemIndexOnSlot(int slotCol, int slotRow) {
        return slotCol + (slotRow * 5);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 120); //a - transparency value 0-255
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }

    public int getXForAlignToRightText(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return tailX - length;
    }
}
