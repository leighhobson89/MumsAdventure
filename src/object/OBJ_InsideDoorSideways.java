package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_InsideDoorSideways extends Entity {

    public OBJ_InsideDoorSideways(GamePanel gp) {

        super(gp);

        name = "InsideDoorSideways";
        down1 = setup("/objects/insideDoorSideways", gp.tileSize, gp.tileSize);
        direction = "down";

        collectable = false;
        isOpenable = true;
    }
}
