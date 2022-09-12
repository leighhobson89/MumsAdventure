package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoor extends Entity {

    public OBJ_FrontDoor(GamePanel gp) {

        super(gp);

        name = "FrontDoor";
        down1 = setup("/objects/frontDoor");
        direction = "down";
        //collision = true;

    }
}
