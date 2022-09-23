package tile_interactive;

import entity.Entity;
import main.GamePanel;

public class IT_BareRockery extends InteractiveTile {

    GamePanel gp;

    public IT_BareRockery(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "Bare Rockery";
        down1 = setup("/tiles_interactive/bareRockery", gp.tileSize, gp.tileSize);
        direction = "down";

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}