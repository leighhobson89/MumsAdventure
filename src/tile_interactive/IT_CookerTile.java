package tile_interactive;

import main.GamePanel;

public class IT_CookerTile extends InteractiveTile {

    GamePanel gp;
    public static final String itName = "IT_CookerTile";

    public IT_CookerTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = itName;
        down1 = setup("/tiles_interactive/cookerTile", gp.tileSize, gp.tileSize);
        direction = "down";
    }
}
