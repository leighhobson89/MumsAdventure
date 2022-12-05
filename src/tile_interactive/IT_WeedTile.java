package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_WeedTile extends InteractiveTile {

    GamePanel gp;

    public IT_WeedTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_Weed";
        down1 = setup("/tiles_interactive/rockeryWorked1", gp.tileSize, gp.tileSize);
        direction = "down";

        destructible = true;
        stressLevel = 0;
        maxStress = 2;
    }

    public boolean isCorrectItem(Entity entity) {

        return entity.currentWeapon.type == type_gardeningShovel;
    }

    public void playSfx() {
        gp.playSFX(23);
    }

    public InteractiveTile switchForm() {
        return new IT_BareRockery(gp, worldX/gp.tileSize, worldY/gp.tileSize);
    }

    public Color getParticleColor() {
        return new Color(65,50,30);
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
