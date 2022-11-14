package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shovel extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Garden Shovel";

    public OBJ_Shovel(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_gardeningShovel;
        name = OBJ_NAME;
        displayName = "Garden Shovel";
        down1 = setup("/objects/shovel", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nDig up the Rockery!";
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        isWeapon = true;
        motion1_duration = 15; //30
        motion2_duration = 40; //75
        isSaleable = false;
    }
}
