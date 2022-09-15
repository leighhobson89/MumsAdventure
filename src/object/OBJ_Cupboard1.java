package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cupboard1 extends Entity {

    public OBJ_Cupboard1(GamePanel gp) {

        super(gp);

        name = "Cupboard1";
        down1 = setup("/objects/cupboard1", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;
        collectable = false;
        isOpenable = false;

    }
}
