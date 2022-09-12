package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoor extends Entity {

    public OBJ_FrontDoor(GamePanel gp) {

        super(gp);

        name = "FrontDoor";
        down1 = setup("/objects/frontDoor", gp.tileSize, gp.tileSize);
        direction = "down";
        //collision = true;

    }
}
