package main;

import object.OBJ_FrontDoorKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font uniFont, breathFire_40, breathFire_80;;
    BufferedImage keyImage;
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

        OBJ_FrontDoorKey frontDoorKey = new OBJ_FrontDoorKey(gp);
        keyImage = frontDoorKey.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public String convertBooleanKeyToString() {
        String keyOrNot;
        if (!gp.player.hasFrontDoorKey) {
            keyOrNot = "No";
        } else {
            keyOrNot = "Yes";
        }
        return keyOrNot;
    }

    public void draw(Graphics2D g2) {
        String text;
        int textLength;
        int x;
        int y;

        if (gameFinished) {
            g2.setFont(breathFire_40);
            g2.setColor(Color.WHITE);

            text = "You Escaped through the gate!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //align text in center
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //align text in center
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            g2.setFont(breathFire_80);
            g2.setColor(Color.YELLOW);

            text = "Congratulations!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth(); //align text in center
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(breathFire_40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("  - " + convertBooleanKeyToString(), 50, 60);

            //TIME
            playTime += (double)1/60;
            g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*11, 65);

            //MESSAGE
            if(messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                textLength = (int)g2.getFontMetrics().getStringBounds(message, g2).getWidth(); //align text in center
                x = gp.screenWidth/2 - textLength/2;
                y = gp.screenHeight/2 + (gp.tileSize*2);
                g2.drawString(message, x, y);

                messageCounter++;

                if (messageCounter > 240) { //4 seconds at 60FPS
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
