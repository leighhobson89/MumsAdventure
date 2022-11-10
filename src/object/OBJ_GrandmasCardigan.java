package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GrandmasCardigan extends Entity {

    public static final String OBJ_NAME = "Old Cardigan";

    public OBJ_GrandmasCardigan(GamePanel gp) {
        super(gp);

        type = type_armour;
        name = OBJ_NAME;
        displayName = "Old Cardigan";
        down1 = setup("/objects/grandmasCardigan", gp.tileSize, gp.tileSize);
        direction = "down";
        defenseValue = 1;
        description = "[" + name + "]\nAn Old Cardigan";
        isSaleable = true;
        price = 20;

        isArmour = true;
    }
}
