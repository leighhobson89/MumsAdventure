package tile_interactive;

import main.GamePanel;

public class IT_BookHut2_Left_TR extends InteractiveTile {

    GamePanel gp;

    public IT_BookHut2_Left_TR(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_switchable_interactive_tile;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_BookHut2_Left_TR";
        down1 = setup("/tiles_interactive/bookhut2_left_tr", gp.tileSize, gp.tileSize);
        direction = "down";

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    public InteractiveTile switchForm() {
        InteractiveTile tile = new IT_BookHut2_Left(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
