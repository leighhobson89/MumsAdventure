package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_InsideDoor extends Entity {

    public OBJ_InsideDoor(GamePanel gp) {

        super(gp);

        name = "InsideDoor";
        down1 = setup("/objects/insideDoor", gp.tileSize, gp.tileSize);
        direction = "down";

       // collision = true;

    }
}
