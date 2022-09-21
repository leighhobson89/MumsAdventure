package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_WeedTile extends InteractiveTile {

    GamePanel gp;

    public IT_WeedTile(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "Weed Tile";
        down1 = setup("/tiles_interactive/rockeryWorked1", gp.tileSize, gp.tileSize);
        direction = "down";

        destructible = true;
        stressLevel = 0;
        maxStress = 2;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrectItem = false;

        if(entity.currentWeapon.type == type_gardeningShovel) {
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSfx() {
        gp.playSFX(23);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new IT_BareRockery(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
