package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontBackDoorOpen extends Entity {

    public static final String OBJ_NAME = "FrontBackDoorOpen";

    public OBJ_FrontBackDoorOpen(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "FrontBackDoorOpen";
        down1 = setup("/objects/frontBackDoorOpen", gp.tileSize, gp.tileSize);
        direction = "down";
    }
}
