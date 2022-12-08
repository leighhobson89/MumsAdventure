package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_WaterTile extends InteractiveTile {

    final GamePanel gp;

    public IT_WaterTile(GamePanel gp, int col, int row) {
        super(gp);
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

        return entity.currentWeapon.type == type_mop;
    }

    public void playSfx() {
        gp.playSFX(31);
    }

    public InteractiveTile switchForm() {
        return new IT_Bathroom(gp, worldX/gp.tileSize, worldY/gp.tileSize);
    }

    public Color getParticleColor() {
        return new Color(54, 84, 194);
    }
    public int getParticleSize() {
        return 6;
    }
    public int getParticleSpeed() {
        return 1;
    }
    public int getParticleMaxLife() {
        return 20;
    }
}
