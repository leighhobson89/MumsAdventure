package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoorKey extends Entity {

    public OBJ_FrontDoorKey(GamePanel gp) {

        super(gp);

        name = "FrontDoorKey";
        down1 = setup("/objects/frontDoorKey", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
