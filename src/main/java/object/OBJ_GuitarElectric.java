package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_GuitarElectric extends Entity {

    public static final String OBJ_NAME = "Electric Guitar";

    public OBJ_GuitarElectric(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "Electric Guitar";
        down1 = setup("/objects/guitar2", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nElectric Guitar";
        price = 100;
        isSaleable = false;

    }
}
