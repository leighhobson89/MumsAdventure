package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cupboard2 extends Entity {

    public OBJ_Cupboard2(GamePanel gp) {

        super(gp);

        name = "Cupboard2";
        down1 = setup("/objects/cupboard2", gp.tileSize, gp.tileSize);
        direction = "down";
        collision = true;
        collectable = false;
        isOpenable = false;

    }
}
