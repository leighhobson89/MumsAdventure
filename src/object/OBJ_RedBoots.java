package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_RedBoots extends Entity {

    public static final String OBJ_NAME = "Red Boots";

    public OBJ_RedBoots(GamePanel gp) {
        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Red Boots";
        down1 = setup("/objects/redBoots", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nSexy Red Boots!";
        isSaleable = true;
        price = 20;
    }

}
