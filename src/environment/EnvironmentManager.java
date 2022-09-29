package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {

    GamePanel gp;
    Lighting lighting;
    final int CIRCLE_SIZE = 576; //size of circle in lighting

    public EnvironmentManager(GamePanel gp) {
        this.gp = gp;
    }

    public void setup() {
        lighting = new Lighting(gp, CIRCLE_SIZE);
    }
    public void draw(Graphics2D g2) {
        lighting.draw(g2);
    }
}
