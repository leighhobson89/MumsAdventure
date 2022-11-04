package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Grey extends Entity {

    public OBJ_Bin_Grey(GamePanel gp) {

        super(gp);

        type = type_obstacle;
        name = "Bin_Grey";
        displayName = "Bin_Grey";
        down1 = setup("/objects/bin_Grey", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
