package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontBackDoorOpen extends Entity {

    public OBJ_FrontBackDoorOpen(GamePanel gp) {

        super(gp);

        name = "FrontBackDoorOpen";
        displayName = "FrontBackDoorOpen";
        down1 = setup("/objects/frontBackDoorOpen", gp.tileSize, gp.tileSize);
        direction = "down";
    }
}
