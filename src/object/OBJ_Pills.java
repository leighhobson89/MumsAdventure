package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pills extends Entity {

    public OBJ_Pills(GamePanel gp) {

        super(gp);

        name = "Pills";
        down1 = setup("/objects/pills", gp.tileSize, gp.tileSize);
        direction = "down";

    }
}
