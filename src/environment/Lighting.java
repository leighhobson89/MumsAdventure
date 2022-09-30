package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp;
    BufferedImage brightFilter;

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() { // implement this
        // Create a buffered image
        brightFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D)brightFilter.getGraphics();

        if(gp.player.currentLight == null) {
            g2.setColor(new Color(255, 255, 255, 200));
        } else {
            //Get the center x and y of the visible circle
            int centerX = gp.player.screenX + (gp.tileSize)/2;
            int centerY = gp.player.screenY + (gp.tileSize)/2;

            // Create a gradient effect within visible circle
            Color color[] = new Color[8];
            float fraction[] = new float[8];

            color[0] = new Color(255,255,255, 25);
            color[1] = new Color(255,255,255,90);
            color[2] = new Color(255,255,255,100);
            color[3] = new Color(255,255,255,125);
            color[4] = new Color(255,255,255,140);
            color[5] = new Color(255,255,255,155);
            color[6] = new Color(255,255,255,170);
            color[7] = new Color(79, 79, 79,185);

            fraction[0] = 0.1f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;

            // Create a gradient paint settings for the visible circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Set the gradient data on g2
            g2.setPaint(gPaint);
        }

        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void update() {
        if (gp.player.lightUpdated) {
            setLightSource();
            gp.player.lightUpdated = false;
        }
    }
    public void draw(Graphics2D g2) {
        g2.drawImage(brightFilter, 0, 0, null);
    }


}
