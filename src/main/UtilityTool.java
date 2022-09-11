package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    public String parseNumberString(int value) {
        String number = "";
        switch(value) {
            case 1:
                number = "one";
                break;
            case 2:
                number = "two";
                break;
            case 3:
                number = "three";
                break;
            case 4:
                number = "four";
                break;
            case 5:
                number = "five";
                break;
            case 6:
                number = "six";
                break;
            case 7:
                number = "seven";
                break;
            case 8:
                number = "eight";
                break;
            case 9:
                number = "nine";
                break;
            case 10:
                number = "ten";
                break;
        }
        return number;
    }
}
