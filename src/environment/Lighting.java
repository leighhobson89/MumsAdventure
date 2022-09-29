package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage brightFilter;

    public Lighting(GamePanel gp, float circleSize) {
        // Create a buffered image
        brightFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)brightFilter.getGraphics();

        //Create a screen-sized rectangle area
        Area screenArea = new Area(new Rectangle2D.Double(0,0,gp.screenWidth, gp.screenHeight));

        //Get the center x and y of the visible circle
        int centerX = gp.player.screenX + (gp.tileSize)/2;
        int centerY = gp.player.screenY + (gp.tileSize)/2;

        //get the top left x and y of the visible circle
        double x = centerX - (circleSize/2);
        double y = centerY - (circleSize/2);

        // Create a light circle shape
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        // Create a light circle area
        Area lightArea = new Area(circleShape);

        //Subtract the visible circle from the screen rectangle
        screenArea.subtract(lightArea);

        // Create a gradient effect within visible circle
        Color color[] = new Color[8];
        float fraction[] = new float[8];

        color[0] = new Color(255,255,255, 25);
        color[1] = new Color(255,255,255,100);
        color[2] = new Color(255,255,255,125);
        color[3] = new Color(255,255,255,145);
        color[4] = new Color(255,255,255,160);
        color[5] = new Color(255,255,255,175);
        color[6] = new Color(255,255,255,190);
        color[7] = new Color(255,255,255,205);

        fraction[0] = 0.1f;
        fraction[1] = 0.4f;
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;

        // Create a gradient paint settings for the visible circle
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);

        // Set the gradient data on g2
        g2.setPaint(gPaint);

        // Draw the visible circle
        g2.fill(lightArea);

//        // Set a color (white) to draw the rectangle
//        g2.setColor(new Color(255,255,255, 200));

        // Draw the screen rectangle without the visible circle area
        g2.fill(screenArea);

        g2.dispose();
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(brightFilter, 0, 0, null);
    }
}
