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
    Font uniFont, breathFire_40, breathFire_80;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue;

    public UI(GamePanel gp) throws IOException, FontFormatException {
        this.gp = gp;

        InputStream iS = this.getClass().getResourceAsStream("/fonts/breatheFire.otf");
        uniFont = Font.createFont(Font.TRUETYPE_FONT,iS);
        breathFire_40 = uniFont.deriveFont(40F);
        breathFire_80 = uniFont.deriveFont(80F);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(breathFire_40);
        g2.setColor(Color.WHITE);

        if (gp.gameState == gp.playState) {
            // Do playState stuff later
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
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

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
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
