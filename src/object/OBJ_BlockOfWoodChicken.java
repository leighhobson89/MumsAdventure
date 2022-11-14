package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlockOfWoodChicken extends Entity {

    public static final String OBJ_NAME = "BlockOfWoodChicken";

    public OBJ_BlockOfWoodChicken(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Chopping Block";
        down1 = setup("/objects/blockOfWoodChicken", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
