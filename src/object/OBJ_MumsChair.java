package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_MumsChair extends Entity {

    public OBJ_MumsChair(GamePanel gp) {

        super(gp);

        name = "MumsChair";
        down1 = setup("/objects/mumsChair", gp.tileSize, gp.tileSize);
        direction = "down";
    }
}
