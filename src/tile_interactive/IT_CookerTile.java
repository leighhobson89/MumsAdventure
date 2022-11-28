package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_CookerTile extends InteractiveTile {

    GamePanel gp;
    public static final String itName = "OldCooker";

    public IT_CookerTile(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_CookerTile";
        down1 = setup("/tiles_interactive/cookerTile", gp.tileSize, gp.tileSize);
        direction = "down";
    }
}
