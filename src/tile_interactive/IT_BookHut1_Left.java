package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class IT_BookHut1_Left extends InteractiveTile {

    GamePanel gp;

    public IT_BookHut1_Left(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_switchable_interactive_tile;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_BookHut1_Left";
        down1 = setup("/tiles_interactive/bookHut1_left", gp.tileSize, gp.tileSize);
        direction = "down";
    }

    public InteractiveTile switchForm() {
        InteractiveTile tile = new IT_BookHut1_Left_TR(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
