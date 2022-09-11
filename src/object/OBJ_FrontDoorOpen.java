package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoorOpen extends Entity {

    public OBJ_FrontDoorOpen(GamePanel gp) {

        super(gp);

        name = "FrontDoorOpen";
        down1 = setup("/objects/frontDoorOpen");
        direction = "down";

    }
}
