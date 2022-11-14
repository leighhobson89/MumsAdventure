package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ChoppedChicken extends Entity {

    public static final String OBJ_NAME = "Chopped Chicken";

    public OBJ_ChoppedChicken(GamePanel gp) {

        super(gp);

        isUpdateable = false;
        name = OBJ_NAME;
        displayName = "some chopped up Raw Chicken";
        down1 = setup("/objects/chicken", gp.tileSize, gp.tileSize);
        direction = "down";
        description = "[" + name + "]\nChopped up Raw Chicken";
        price = 0;
        stackable = true;
        isSaleable = false;
    }
}
