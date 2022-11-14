package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chicken extends Entity {

    public static final String OBJ_NAME = "Chicken";

    public OBJ_Chicken(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "some Raw Chicken";
        down1 = setup("/objects/chicken", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nSome Raw Chicken";
        price = 0;
        isSaleable = false;
    }
}
