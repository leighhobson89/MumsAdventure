package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lavender_Crocs extends Entity {

    public static final String OBJ_NAME = "Lavender Crocs";

    public OBJ_Lavender_Crocs(GamePanel gp) {
        super(gp);

        isUpdateable = false;
        type = type_short_weapon;
        name = OBJ_NAME;
        displayName = "Lavender Crocs";
        down1 = setup("/objects/lavendarCrocs", gp.tileSize, gp.tileSize);
        direction = "down";
        attackValue = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nYour Favourite Lavender\nCrocs!";
        isSaleable = true;
        price = 7;
        knockBackPower = 3;
        isWeapon = true;
        motion1_duration = 5;
        motion2_duration = 25;
    }

}
