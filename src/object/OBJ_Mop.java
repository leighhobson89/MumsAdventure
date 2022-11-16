package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mop extends Entity {

    GamePanel gp;
    public static final String OBJ_NAME = "Mop";

    public OBJ_Mop(GamePanel gp) {

        super(gp);
        this.gp = gp;

        isUpdateable = false;
        type = type_mop;
        name = OBJ_NAME;
        displayName = "Mop";
        down1 = setup("/objects/mop", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nAn old Mop";
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        isWeapon = true;
        motion1_duration = 15; //30
        motion2_duration = 40; //75
        isSaleable = false;
    }
}
