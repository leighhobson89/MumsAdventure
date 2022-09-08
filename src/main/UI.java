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

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

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

//    public String convertBooleanKeyToString() {
//        String keyOrNot;
//        if (!gp.player.hasFrontDoorKey) {
//            keyOrNot = "No";
//        } else {
//            keyOrNot = "Yes";
//        }
//        return keyOrNot;
//    }

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
    }
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
