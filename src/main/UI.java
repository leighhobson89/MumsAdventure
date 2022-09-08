package main;

import object.OBJ_FrontDoorKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font breathFire, maruMonica, breathFire_40, breathFire_80, maruMonica_40;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;
    public int commandNum = 0;
    public int titleScreenState = 0; //0: FIRST TITLE SCREEN 1: SECOND SCREEN
    public int colorOutfit = 0;

    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;

        InputStream iS = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
        maruMonica = Font.createFont(Font.TRUETYPE_FONT,iS);
//        iS = getClass().getResourceAsStream("/fonts/breatheFire.otf");
//        breathFire = Font.createFont(Font.TRUETYPE_FONT,iS);

//        breathFire_40 = breathFire.deriveFont(40F);
//        breathFire_80 = breathFire.deriveFont(80F);
        maruMonica_40 = maruMonica.deriveFont(40F);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
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
            // Do playState stuff later
        }
        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
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
                colorOutfit = 0;
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
                colorOutfit = 1;
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
                colorOutfit = 2;
            }

            text = "Back";
            x = getXforCenteredText(text);
            y = gp.tileSize*11;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x-gp.tileSize, y);
                colorOutfit = 1;
            }

            x = gp.screenWidth/2 - (gp.tileSize*2)/2;
            y = gp.tileSize*5;
            switch (colorOutfit) {
                case 0:
                    g2.drawImage(gp.player.down1_red, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
                case 1:
                    g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
                case 2:
                    g2.drawImage(gp.player.down1_purple, x, y, gp.tileSize*2, gp.tileSize*2, null);
                    break;
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

    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 170); //a - transparency value 0-255
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
