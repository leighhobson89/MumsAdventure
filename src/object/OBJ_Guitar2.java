package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Guitar2 extends Entity {

    public static final String OBJ_NAME = "Electric Guitar";

    public OBJ_Guitar2(GamePanel gp) {

        super(gp);

        name = OBJ_NAME;
        displayName = "Electric Guitar";
        down1 = setup("/objects/guitar2", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nElectric Guitar";
        price = 100;
        isSaleable = false;

    }
}
