package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FrontDoorKey extends Entity {

    public OBJ_FrontDoorKey(GamePanel gp) {

        super(gp);

        name = "Key";
        down1 = setup("/objects/frontDoorKey", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nKey For Front And\nBack Door";
        collectable = true;
        isOpenable = false;

    }
}
