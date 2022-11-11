package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Hatchet extends Entity {

    public static final String OBJ_NAME = "Hatchet";

    public OBJ_Hatchet(GamePanel gp) {
        super(gp);

        type = type_axe;
        name = OBJ_NAME;
        displayName = "an Axe";
        down1 = setup("/objects/hatchet", gp.tileSize, gp.tileSize);
        direction = "down";
        attackValue = 5;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAxe for chopping chicken\nfor Phoebe & Pip!";
        price = 30;
        knockBackPower = 8;
        isWeapon = true;
        motion1_duration = 4;
        motion2_duration = 20;
        isSaleable = true;
    }

}
