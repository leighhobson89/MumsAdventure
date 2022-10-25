package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Guitar1 extends Entity {

    public OBJ_Guitar1(GamePanel gp) {

        super(gp);

        name = "Acoustic Guitar";
        down1 = setup("/objects/guitar1", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nAcoustic Guitar";
        price = 80;
        isSaleable = true;

    }
}
