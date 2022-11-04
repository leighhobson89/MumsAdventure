package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bin_Grey extends Entity {

    public static final String OBJ_NAME = "Bin_Grey";

    public OBJ_Bin_Grey(GamePanel gp) {

        super(gp);

        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Bin_Grey";
        down1 = setup("/objects/bin_Grey", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
