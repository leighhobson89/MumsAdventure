package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_InsideDoorOpen extends Entity {

    public OBJ_InsideDoorOpen(GamePanel gp) {

        super(gp);

        name = "InsideDoorOpen";
        down1 = setup("/objects/insideDoorOpen");
        direction = "down";

    }
}
