package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Guitar2 extends Entity {

    public OBJ_Guitar2(GamePanel gp) {

        super(gp);

        name = "Guitar2";
        down1 = setup("/objects/guitar2", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nElectric Guitar";

    }
}
