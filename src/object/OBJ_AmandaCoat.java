package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_AmandaCoat extends Entity {

    public static final String OBJ_NAME = "Coat off Amanda";

    public OBJ_AmandaCoat(GamePanel gp) {
        super(gp);

        isUpdateable = false;
        type = type_armour;
        name = OBJ_NAME;
        displayName = "Blue Coat";
        down1 = setup("/objects/amandaCoat", gp.tileSize, gp.tileSize);
        direction = "down";
        defenseValue = 2;
        description = "[" + displayName + "]\nA Blue Coat";
        isSaleable = true;
        price = 0;

        isArmour = true;
    }
}
