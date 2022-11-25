package tile_interactive;

import main.GamePanel;

public class IT_BookHut1_Right extends InteractiveTile {

    GamePanel gp;

    public IT_BookHut1_Right(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        type = type_switchable_interactive_tile;
        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;
        name = "IT_BookHut1_Right";
        down1 = setup("/tiles_interactive/bookHut1_right", gp.tileSize, gp.tileSize);
        direction = "down";
    }

    public InteractiveTile switchForm() {
        InteractiveTile tile = new IT_BookHut1_Right_TR(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
    }
}
