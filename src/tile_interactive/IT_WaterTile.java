package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_WaterTile extends InteractiveTile {

    GamePanel gp;

    public IT_WaterTile(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_Water";
        down1 = setup("/tiles_interactive/waterTile", gp.tileSize, gp.tileSize);
        direction = "down";

        destructible = true;
        stressLevel = 0;
        maxStress = 2;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if(entity.currentWeapon.type == type_mop) {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSfx() {
        gp.playSFX(31);
    }

    public InteractiveTile switchForm() {
        InteractiveTile tile = new IT_Bathroom(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }

    public Color getParticleColor() {
        Color color = new Color(54, 84, 194);
        return color;
    }
    public int getParticleSize() {
        int size = 6; //6 pixels
        return size;
    }
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife() {
        int maxLife = 20;
        return maxLife;
    }
}
