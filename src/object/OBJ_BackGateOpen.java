package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BackGateOpen extends Entity {

    public OBJ_BackGateOpen(GamePanel gp) {

        super(gp);

        name = "BackGateOpen";
        direction = "down";
        down1 = setup("/objects/backGateOpen", gp.tileSize, gp.tileSize);
        collectable = false;
        isOpenable = false;

    }
}
