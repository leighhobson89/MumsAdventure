package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_TV_Remote extends Entity {

    public static final String OBJ_NAME = "TVRemote";

    public OBJ_TV_Remote(GamePanel gp) {
        super(gp);

        isUpdateable = false;
        type = type_tv_remote;
        name = OBJ_NAME;
        displayName = "the TV remote";
        down1 = setup("/objects/tvRemote", gp.tileSize, gp.tileSize);
        direction = "down";
        attackValue = 0;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nThe TV remote!";
        isSaleable = false;
        isWeapon = true;
        motion1_duration = 5;
        motion2_duration = 25;
    }

}
