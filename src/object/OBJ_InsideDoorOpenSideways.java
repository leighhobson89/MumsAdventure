package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_InsideDoorOpenSideways extends Entity {

    public OBJ_InsideDoorOpenSideways(GamePanel gp) {

        super(gp);

        name = "InsideDoorOpenSideways";
        down1 = setup("/objects/insideDoorOpenSideways", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
