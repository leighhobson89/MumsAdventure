package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cupboard1 extends Entity {

    public static final String OBJ_NAME = "Cupboard1";

    public OBJ_Cupboard1(GamePanel gp) {

        super(gp);

        name = OBJ_NAME;
        displayName = "Cupboard1";
        down1 = setup("/objects/cupboard1", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;

    }
}
