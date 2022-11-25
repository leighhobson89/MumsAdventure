package tile_interactive;

import main.GamePanel;

public class IT_BookHut3_Left extends InteractiveTile {

    GamePanel gp;

    public IT_BookHut3_Left(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_switchable_interactive_tile;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_BookHut3_Left";
        down1 = setup("/tiles_interactive/bookHut3_left", gp.tileSize, gp.tileSize);
        direction = "down";
    }

    public InteractiveTile switchForm() {
        InteractiveTile tile = new IT_BookHut3_Left_TR(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
