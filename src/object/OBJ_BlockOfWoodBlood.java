package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlockOfWoodBlood extends Entity {

    public static final String OBJ_NAME = "BlockOfWoodBlood";

    public OBJ_BlockOfWoodBlood(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        type = type_obstacle;
        name = OBJ_NAME;
        displayName = "Chopping Block";
        down1 = setup("/objects/blockOfWoodBlood", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
